package com.drink.ordering.system.barista.service.domain.event;

import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.common.domain.event.DomainEvent;

public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;

    public OrderEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
