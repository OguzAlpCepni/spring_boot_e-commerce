package ecommerce.ecommerce.service.abstracts;


import ecommerce.ecommerce.core.Dtos.BasketDtos.BasketDto;

public interface BasketService {

    public BasketDto getAllItemsInBasket();
    public void addItemsCustomerBasket(String productId, int quantity);
    public void removeItemsCustomerBasket(String productId);
    public void removeItemsCustomerBasketWithQuantity(String productId, int quantity);
    public void updateItemsCustomerBasket(String productId, int quantity);
}
