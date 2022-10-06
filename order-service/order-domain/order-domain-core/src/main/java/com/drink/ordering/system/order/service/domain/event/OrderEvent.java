package com.drink.ordering.system.order.service.domain.event;

import com.drink.ordering.system.common.domain.event.DomainEvent;
import com.drink.ordering.system.order.service.domain.entity.Order;

public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;

    public OrderEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
