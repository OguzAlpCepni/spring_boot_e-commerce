package ecommerce.ecommerce.service.abstracts;


import ecommerce.ecommerce.core.Dtos.OrderDtos.OrderDto;


public interface OrderService {

    public OrderDto createOrderFromBasket();
    public void processPayment(String orderId,boolean paymentSuccesfully);


}