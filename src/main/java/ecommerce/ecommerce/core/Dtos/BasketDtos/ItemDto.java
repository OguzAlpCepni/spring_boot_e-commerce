package ecommerce.ecommerce.core.Dtos.BasketDtos;

import ecommerce.ecommerce.model.Basket;

import ecommerce.ecommerce.model.Product;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private String id;
    private String basketId; // Basket yerine sadece basketId
    private int quantity;
    private BigDecimal itemPrice;
    private String productId; // Product yerine sadece productId
}
