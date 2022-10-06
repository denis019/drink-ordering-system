package com.drink.ordering.system.order.service.dataaccess.order.mapper;

import com.drink.ordering.system.common.domain.valueobject.DrinkAddition;
import com.drink.ordering.system.common.domain.valueobject.OrderCreatedAt;
import com.drink.ordering.system.common.domain.valueobject.OrderId;
import com.drink.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.drink.ordering.system.order.service.domain.entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.drink.ordering.system.order.service.domain.entity.Order.LIST_DELIMITER;

public class OrderDataAccessMapper {

    public static OrderEntity orderToOrderEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId().getValue())
                .drinkType(order.getDrinkType())
                .drinkAdditions(order.getDrinkAdditions() != null ?
                        order.getDrinkAdditions().stream().map(DrinkAddition::name)
                                        .collect(Collectors.joining(LIST_DELIMITER)) : "")
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public static Order orderEntityToOrder(OrderEntity orderEntity) {
        List<String> drinkAdditionsRaw = orderEntity.getDrinkAdditions().isEmpty() ? new ArrayList<>() :
                new ArrayList<>(Arrays.asList(orderEntity.getDrinkAdditions().split(LIST_DELIMITER)));

        List<DrinkAddition> drinkAdditions = drinkAdditionsRaw
                .stream()
                .map(DrinkAddition::fromString)
                .toList();


        return Order.builder()
                .orderId(new OrderId(orderEntity.getId()))
                .drinkType(orderEntity.getDrinkType())
                .drinkAdditions(drinkAdditions)
                .orderStatus(orderEntity.getOrderStatus())
                .createdAt(new OrderCreatedAt(orderEntity.getCreatedAt()))
                .build();
    }
}
