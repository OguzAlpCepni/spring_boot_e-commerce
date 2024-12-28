package ecommerce.ecommerce.service.abstracts;


import ecommerce.ecommerce.core.Dtos.OrderDtos.OrderDto;
import ecommerce.ecommerce.core.Dtos.payment.PaymentRequest;
import ecommerce.ecommerce.model.Basket;


public interface OrderService {

    public OrderDto createOrderFromBasket();
    public void processPayment(String orderId, PaymentRequest paymentRequest);


}