package ecommerce.ecommerce.core.Dtos.BasketDtos;

import ecommerce.ecommerce.model.BasketItem;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {

    private String basketId;
    private List<ItemDto> basketItems;
    private BigDecimal totalPrice;

}
