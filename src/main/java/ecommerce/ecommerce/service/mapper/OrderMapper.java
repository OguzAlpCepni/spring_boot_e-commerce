package ecommerce.ecommerce.service.mapper;

import ecommerce.ecommerce.core.Dtos.OrderDtos.OrderDto;
import ecommerce.ecommerce.core.Dtos.OrderDtos.OrderItemDto;
import ecommerce.ecommerce.core.Dtos.OrderDtos.Status;
import ecommerce.ecommerce.model.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class OrderMapper {

    public OrderDto convertToOrderDto(Order order) {
        // Sipariş öğelerini dönüştür
        List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
                .map(orderItem -> {
                    OrderItemDto orderItemDto = new OrderItemDto();
                    orderItemDto.setId(orderItem.getId());
                    orderItemDto.setItemPrice(orderItem.getItemPrice());
                    orderItemDto.setQuantity(orderItem.getQuantity());
                    orderItemDto.setProductId(orderItem.getProduct().getProductId());
                    return orderItemDto;
                })
                .collect(Collectors.toList());

        // Sipariş DTO'sunu oluştur
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderItems(orderItemDtos);
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setStatus(Status.valueOf(order.getStatus().name())); // Enum dönüşümü
        return orderDto;
    }
}