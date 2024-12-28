package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.core.Dtos.OrderDtos.OrderDto;
import ecommerce.ecommerce.core.Dtos.payment.PaymentRequest;
import ecommerce.ecommerce.service.abstracts.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrderFromBasket")
    public ResponseEntity<OrderDto> createOrderFromBasket(){
        return ResponseEntity.ok(orderService.createOrderFromBasket());
    }

    @PostMapping("/process/orderId/{orderId}")
    public ResponseEntity processPayment(@PathVariable String orderId, @RequestBody PaymentRequest paymentRequest){
        try {
            orderService.processPayment(orderId, paymentRequest);
            return ResponseEntity.ok("Payment is successful");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the payment ");
        }

    }
}
