package com.drink.ordering.system.order.service.domain.mapper;

import com.drink.ordering.system.common.domain.valueobject.DrinkType;
import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.drink.ordering.system.order.service.domain.dto.find.FindOrderResponse;
import com.drink.ordering.system.order.service.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {

    public static Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .drinkAdditions(createOrderCommand.getDrinkAdditions())
                .drinkType(DrinkType.fromString(createOrderCommand.getDrinkType().name()))
                .build();
    }

    public static CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public static FindOrderResponse orderToFindOrderResponse(Order order) {
        return FindOrderResponse.builder()
                .orderId(order.getId().getValue())
                .drinkType(order.getDrinkType())
                .drinkAdditions(order.getDrinkAdditions())
                .orderStatus(order.getOrderStatus())
                .orderCreatedAt(order.getCreatedAt().getValue())
                .build();
    }
}
