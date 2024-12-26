package ecommerce.ecommerce.service.concreate;

import ecommerce.ecommerce.core.Dtos.BasketDtos.BasketDto;
import ecommerce.ecommerce.model.Basket;
import ecommerce.ecommerce.model.BasketItem;
import ecommerce.ecommerce.model.Product;
import ecommerce.ecommerce.repository.BasketRepository;
import ecommerce.ecommerce.service.BusinessRules.BasketRules;
import ecommerce.ecommerce.service.abstracts.BasketService;
import ecommerce.ecommerce.service.mapper.BasketMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class BasketManager implements BasketService {

    private final BasketRepository basketRepository;
    private final BasketRules basketRules;
    private final BasketMapper basketMapper;

    public BasketManager(BasketRepository basketRepository, BasketRules basketRules, BasketMapper basketMapper) {
        this.basketRepository = basketRepository;
        this.basketRules = basketRules;
        this.basketMapper = basketMapper;
    }

    @Override
    public BasketDto getAllItemsInBasket() {
        Basket basket = basketRules.getCustomerBasket();
        basketRules.calculateTotalBasketPrice(basket);
        return basketMapper.converToBasketDto(basket);
    }

    @Override
    public void addItemsCustomerBasket(String productId, int quantity) {
        // mevcut kullanıcının sepetini aldım
       Basket basket = basketRules.getCustomerBasket();
       // ürünü ıdsi ile buldum
       Product product = basketRules.getProductById(productId);
       // şimdi yapılamsı gereken sepette bu ürün var mı kontrolü
        basketRules.CheckTheBasketAfterUpdateQuantityOrCreateBasketItem(basket, product, quantity);
        // yapılan iş basketin toplam fiyat tutarının hesaplanması
        basketRules.calculateTotalBasketPrice(basket);
        basketRepository.save(basket);
    }

    @Override
    public void removeItemsCustomerBasket(String productId) {
        Basket basket = basketRules.getCustomerBasket();
        BasketItem basketItem = basket.getBasketItems().stream().filter(item -> item.getProduct().getProductId().equals(productId)).findFirst().orElseThrow(() -> new RuntimeException("No such item"));
        basket.getBasketItems().remove(basketItem);
        basketRules.calculateTotalBasketPrice(basket);
        basketRepository.save(basket);
    }

    public void removeItemsCustomerBasketWithQuantity(String productId, int quantity) {
        Basket basket = basketRules.getCustomerBasket();
        basketRules.checkTheBasketAndRemoveBasketItemsAccordingToQuantity(basket,productId,quantity);
        basketRules.calculateTotalBasketPrice(basket);
        basketRepository.save(basket);

    }

    @Override
    public void updateItemsCustomerBasket(String productId, int quantity) {
        Basket basket = basketRules.getCustomerBasket();
        Product product = basketRules.getProductById(productId);
        basketRules.CheckTheBasketAndUpdateBasket(basket,product,quantity);
        basketRepository.save(basket);
    }
}
