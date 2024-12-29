package ecommerce.ecommerce.service.BusinessRules;

import ecommerce.ecommerce.core.Dtos.payment.PaymentRequest;
import ecommerce.ecommerce.core.Dtos.payment.PaymentResponse;
import ecommerce.ecommerce.core.exceptions.ProductNotFoundException;
import ecommerce.ecommerce.model.*;
import ecommerce.ecommerce.model.user.User;
import ecommerce.ecommerce.repository.OrderRepository;
import ecommerce.ecommerce.repository.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class OrderRules {


    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderRules(ProductRepository productRepository,OrderRepository orderRepository) {

        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Basket getCustomerBasketForOrder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new IllegalStateException("Authentication object is null.");
        }
        //System.out.println("Authentication Principal: " + authentication.getPrincipal());
        //System.out.println("Authentication Details: " + authentication.getDetails());
        //System.out.println("Authentication Name: " + authentication.getName());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            return user.getBasket();
        }else {
            throw new IllegalStateException("Principal is not an instance of user.");
        }
    }
    public void CheckTheBasketIsEmpty(Basket basket){
        if(basket.getBasketItems().isEmpty()){
            throw new EmptyBasketException("Basket is so dont create order");
        }
    }
    public User getCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new IllegalStateException("Authentication object is null.");
        }
        //System.out.println("Authentication Principal: " + authentication.getPrincipal());
        //System.out.println("Authentication Details: " + authentication.getDetails());
        //System.out.println("Authentication Name: " + authentication.getName());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            return user;
        }else {
            throw new IllegalStateException("Principal is not an instance of user.");
        }

    }

    public void checkTheProductStockForOrder(Basket basket){
        for (BasketItem basketItem: basket.getBasketItems()){
            Product product = productRepository.findById(basketItem.getProduct().getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found"));
            if(product.getUnitInStock() < basketItem.getQuantity()){
                throw new RuntimeException("dont have enough stock" + product.getProductName());
            }

            product.setUnitInStock(product.getUnitInStock()-basketItem.getQuantity());
            productRepository.save(product);
        }
    }

    public Order CreateOrder(Basket basket){
        Order order = new Order();
        order.setOrderItems(
                basket.getBasketItems().stream().map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setProduct(item.getProduct());
                    orderItem.setItemPrice(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

                    return orderItem;
                }).collect(Collectors.toList())
        );
        order.setTotalPrice(basket.getTotalPrice());
        order.setUser(getCustomer());
        order.setStatus(OrderStatus.PENDING);

        return order;
    }

    public void processSuccessfulPayment(Order order) {
        try {
            for (OrderItem orderItem : order.getOrderItems()) {
                updateProductStock(orderItem, -orderItem.getQuantity());
            }
            order.setStatus(OrderStatus.COMPLETED);
            orderRepository.save(order);
        } catch (RuntimeException e) {
            rollbackProductStock(order);
            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
            throw new RuntimeException("An error occurred while processing the payment: " + e.getMessage(), e);
        }
    }
    public void handleFailedPayment(Order order) {
        throw new RuntimeException("Payment was not successful.");
    }

    public void updateProductStock(OrderItem orderItem, int quantityChange) {
        String productId = orderItem.getProduct().getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setUnitInStock(product.getUnitInStock() + quantityChange);
        productRepository.save(product);
    }

    public void rollbackProductStock(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            updateProductStock(orderItem, orderItem.getQuantity());
        }
    }
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        /// Ödeme işlemini simüle et
        if (isValidCard(paymentRequest.getCardNumber())) {
            if (paymentRequest.getAmount() > 0) {
                // Ödeme başarılı
                return new PaymentResponse(true, "Payment successful!");
            } else {
                // Geçersiz tutar
                return new PaymentResponse(false, "Invalid payment amount.");
            }
        } else {
            // Geçersiz kart bilgisi
            return new PaymentResponse(false, "Invalid card details.");
        }
    }
    // Kart doğrulama işlemi (sahte bir doğrulama)
    private boolean isValidCard(String cardNumber) {
        // Basit bir kontrol: Kart numarası 16 haneli olmalı
        return cardNumber != null && cardNumber.length() == 16 && cardNumber.matches("\\d+");
    }
}
