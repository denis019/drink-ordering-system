package com.drink.ordering.system.common.domain.valueobject;

import com.drink.ordering.system.common.domain.exception.OrderStatusNotFoundException;

public enum OrderPreparationStatus {
    ORDER_ACCEPTED, ORDER_CANCELED, ORDER_FINISHED;

    public static OrderPreparationStatus fromString(String rawOrderStatus) {
        for (OrderPreparationStatus drinkType : OrderPreparationStatus.values()) {
            if (drinkType.name().equalsIgnoreCase(rawOrderStatus)) {
                return drinkType;
            }
        }

        throw new OrderStatusNotFoundException("Order Status not found " + rawOrderStatus);
    }
}
