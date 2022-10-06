package com.drink.ordering.system.barista.service.dataaccess.order.mapper;

import com.drink.ordering.system.barista.service.dataaccess.order.entity.OrderEntity;
import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.common.domain.valueobject.OrderId;

public class OrderDataAccessMapper {

    public static OrderEntity orderToOrderEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId().getValue())
                .orderPreparationStatus(order.getOrderPreparationStatus())
                .build();
    }

    public static Order orderEntityToOrder(OrderEntity orderEntity) {
        return Order.builder()
                .orderId(new OrderId(orderEntity.getId()))
                .orderPreparationStatus(orderEntity.getOrderPreparationStatus())
                .build();
    }
}
