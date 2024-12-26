package ecommerce.ecommerce.service.BusinessRules;


import ecommerce.ecommerce.core.exceptions.ProductNotFoundException;
import ecommerce.ecommerce.model.Basket;
import ecommerce.ecommerce.model.BasketItem;
import ecommerce.ecommerce.model.Product;
import ecommerce.ecommerce.model.user.User;
import ecommerce.ecommerce.repository.BasketRepository;
import ecommerce.ecommerce.repository.ProductRepository;
import ecommerce.ecommerce.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Optional;

@Component
public class BasketRules {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;


    public BasketRules(UserRepository userRepository, ProductRepository productRepository, BasketRepository basketRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
    }
    public String getCustomerId(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userId;

    }
    public Basket getCustomerBasket(){
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

    public Product getProductById(String productId){

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("product could not found"));

        return product;
    }

    public void CheckTheBasketAfterUpdateQuantityOrCreateBasketItem(Basket basket ,Product product, int quantity){

        Optional<BasketItem> existingItem = basket.getBasketItems().stream()
                .filter(item-> item.getProduct().getProductId().equals(product.getProductId()))
                .findFirst();

        if(existingItem.isPresent()){
            // ürün sepette önceden eklenmişse
            BasketItem basketItem = existingItem.get();
            if(quantity > 0){
               basketItem.setQuantity(basketItem.getQuantity()+quantity);
               basketItem.setItemPrice(product.getPrice().multiply(BigDecimal.valueOf(basketItem.getQuantity())));
            }else
                basket.getBasketItems().remove(basketItem);// if quantity value is 0
        }
        else if (quantity > 0){
            // ürün önceden eklenmemişse bu işlemi yap
            BasketItem newItem = new BasketItem();
            newItem.setBasket(basket);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setItemPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            basket.getBasketItems().add(newItem);
        }
    }
    public void calculateTotalBasketPrice(Basket basket){

        BigDecimal totalPrice = basket.getBasketItems().stream()
                        .map(item->item.getItemPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

        basket.setTotalPrice(totalPrice);
    }

    public void CheckTheBasketAndUpdateBasket(Basket basket ,Product product, int quantity){
        BasketItem existingItem = basket.getBasketItems().stream()
                .filter(item-> item.getProduct().getProductId().equals(product.getProductId()))
                .findFirst()
                .orElse(null);

        if(existingItem != null){
            existingItem.setQuantity(quantity);
        }else {
            BasketItem newItem = new BasketItem();
            newItem.setBasket(basket);
            newItem.setQuantity(quantity);
            newItem.setItemPrice(product.getPrice());
            newItem.setProduct(product);
            basket.getBasketItems().add(newItem);
        }
            // Toplam fiyatı hesapla
        calculateTotalBasketPrice(basket);

    }
    public void checkTheBasketAndRemoveBasketItemsAccordingToQuantity(Basket basket ,String productId, int quantity){
        Optional<BasketItem> basketItem = basket.getBasketItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId)).findFirst();

        if(basketItem.isPresent()){
            BasketItem item = basketItem.get();

            if(quantity > 0 && item.getQuantity() >= quantity){
                item.setQuantity(item.getQuantity()-quantity);
                if(item.getQuantity() == 0){
                    basket.getBasketItems().remove(item);
                }
            }else {
                throw new IllegalArgumentException("Invalid quantity provided for removal");
            }
        }else {
            throw new IllegalArgumentException("Product not found in the basket");
        }
    }
}
