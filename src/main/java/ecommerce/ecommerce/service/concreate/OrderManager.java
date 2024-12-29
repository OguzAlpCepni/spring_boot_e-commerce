package ecommerce.ecommerce.service.concreate;


import ecommerce.ecommerce.core.Dtos.OrderDtos.OrderDto;
import ecommerce.ecommerce.core.Dtos.payment.PaymentRequest;
import ecommerce.ecommerce.core.Dtos.payment.PaymentResponse;
import ecommerce.ecommerce.model.*;
import ecommerce.ecommerce.repository.BasketRepository;
import ecommerce.ecommerce.repository.OrderRepository;
import ecommerce.ecommerce.repository.ProductRepository;
import ecommerce.ecommerce.service.BusinessRules.OrderRules;
import ecommerce.ecommerce.service.abstracts.OrderService;
import ecommerce.ecommerce.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderManager implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderRules orderRules;
    private final OrderMapper orderMapper;
    private final BasketRepository basketRepository;

    public OrderManager(OrderRepository orderRepository, OrderMapper orderMapper, OrderRules orderRules, ProductRepository productRepository,BasketRepository basketRepository) {
        this.orderRepository = orderRepository;
        this.orderRules = orderRules;
        this.productRepository = productRepository; // will change
        this.orderMapper=orderMapper;
        this.basketRepository=basketRepository;

    }
    @Override
    @Transactional
    public OrderDto createOrderFromBasket() {
        Basket basket = orderRules.getCustomerBasketForOrder();

        orderRules.checkTheProductStockForOrder(basket);
        Order order = orderRules.CreateOrder(basket);
        basket.getBasketItems().clear();
        basketRepository.save(basket);
        orderRepository.save(order);
        return orderMapper.convertToOrderDto(order);
    }

    @Override
    @Transactional
    public void processPayment(String orderId, PaymentRequest paymentRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        PaymentResponse paymentResponse = orderRules.processPayment(paymentRequest);

        if (paymentResponse.isSuccessful()) {
            for (OrderItem orderItem : order.getOrderItems()){
                orderRules.updateProductStock(orderItem,-orderItem.getQuantity());
            }
            order.setStatus(OrderStatus.COMPLETED);
            orderRepository.save(order);
        } else {
            // Ödeme başarısız, siparişi iptal et
            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
        }

    }
}
