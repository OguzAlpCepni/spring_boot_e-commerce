package ecommerce.ecommerce.core.Dtos.OrderDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {

    private String id;
    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal itemPrice;
}
