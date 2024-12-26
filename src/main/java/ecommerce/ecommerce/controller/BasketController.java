package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.core.Dtos.BasketDtos.BasketDto;
import ecommerce.ecommerce.service.abstracts.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }


    @GetMapping
    public ResponseEntity<BasketDto> getBasket() {
        return ResponseEntity.ok(basketService.getAllItemsInBasket());
    }

    @PostMapping("/add/product/{productId}/quantity/{quantity}")
    public ResponseEntity addBasket(@PathVariable String productId,@PathVariable int quantity) {
        basketService.addItemsCustomerBasket(productId,quantity);
        return ResponseEntity.ok("Product added the basket successfully");
    }

    @DeleteMapping("/delete/productId/{productId}")
    public ResponseEntity removeItemsCustomerBasket(@PathVariable String productId) {
        basketService.removeItemsCustomerBasket(productId);
        return ResponseEntity.ok("Product removed successfully");
    }
    @DeleteMapping("delete/productId/{productId}/quantity/{quantity}")
    public ResponseEntity RemoveItemCustomerBasketWithQuantity(@PathVariable String productId,@PathVariable int quantity) {
        basketService.removeItemsCustomerBasketWithQuantity(productId,quantity);
        return ResponseEntity.ok("Product removed successfully");
    }
   @PutMapping("/updateItemsCustomerBasket/productId/{productId}/quantity/{quantity}")
    public ResponseEntity updateItemsCustomerBasket(@PathVariable String productId,@PathVariable int quantity)  {
        basketService.updateItemsCustomerBasket(productId,quantity);
        return ResponseEntity.ok("Product updated successfully");
    }


}
