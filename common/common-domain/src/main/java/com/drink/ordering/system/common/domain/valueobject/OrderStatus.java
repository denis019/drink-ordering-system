package com.drink.ordering.system.common.domain.valueobject;

import com.drink.ordering.system.common.domain.exception.OrderStatusNotFoundException;

public enum OrderStatus {
    PLACED, IN_PROGRESS, FINISHED, DELIVERED;

    public static OrderStatus fromString(String rawOrderStatus) {
        for (OrderStatus drinkType : OrderStatus.values()) {
            if (drinkType.name().equalsIgnoreCase(rawOrderStatus)) {
                return drinkType;
            }
        }

        throw new OrderStatusNotFoundException("Order Status not found " + rawOrderStatus);
    }
}
