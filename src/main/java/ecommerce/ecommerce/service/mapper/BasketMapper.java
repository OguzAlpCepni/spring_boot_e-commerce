package ecommerce.ecommerce.service.mapper;

import ecommerce.ecommerce.core.Dtos.BasketDtos.BasketDto;
import ecommerce.ecommerce.core.Dtos.BasketDtos.ItemDto;
import ecommerce.ecommerce.model.Basket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasketMapper {

    public List<ItemDto> basketItemConvertToDto(Basket basket){
        return basket.getBasketItems().stream().map(
                item -> {
                    ItemDto itemDto = new ItemDto();
                    itemDto.setId(item.getId());
                    itemDto.getItemPrice();
                    itemDto.setQuantity(item.getQuantity());
                    return itemDto;
                }
        ).toList();
    }

    public BasketDto converToBasketDto(Basket basket){
        List<ItemDto> itemDtos = basket.getBasketItems().stream().map(
                item -> {
                    ItemDto itemDto = new ItemDto();
                    itemDto.setId(item.getId());
                    itemDto.setBasketId(basket.getBasketId());
                    itemDto.setItemPrice(item.getItemPrice());
                    itemDto.setQuantity(item.getQuantity());
                    itemDto.setProductId(item.getProduct().getProductId());
                    return itemDto;
                }
        ).toList();

        BasketDto basketDto = new BasketDto();
        basketDto.setBasketId(basket.getBasketId());
        basketDto.setBasketItems(itemDtos);
        basketDto.setTotalPrice(basket.getTotalPrice());
        return basketDto;
    }
}
