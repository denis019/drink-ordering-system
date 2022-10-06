package com.drink.ordering.system.barista.service.domain.mapper;

import com.drink.ordering.system.barista.service.domain.dto.receive.AcceptOrderDto;
import com.drink.ordering.system.barista.service.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {

    public static Order acceptOrderDtoToOrder(AcceptOrderDto acceptOrderDto) {
        return Order.builder()
                .orderId(acceptOrderDto.getOrderId())
                .build();
    }
}
