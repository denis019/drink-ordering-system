package com.drink.ordering.system.order.service.domain.event;

import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;
import com.drink.ordering.system.order.service.domain.entity.Order;

public class OrderCreatedEvent extends OrderEvent {

    private final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher;

    public OrderCreatedEvent(
            Order order,
            DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher
    ) {
        super(order);
        this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderCreatedEventDomainEventPublisher.publish(this);
    }
}
