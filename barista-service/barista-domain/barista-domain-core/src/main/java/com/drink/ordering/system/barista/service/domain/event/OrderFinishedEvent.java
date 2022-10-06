package com.drink.ordering.system.barista.service.domain.event;

import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;

public class OrderFinishedEvent extends OrderEvent {

    private final DomainEventPublisher<OrderFinishedEvent> orderFinishedEventDomainEventPublisher;

    public OrderFinishedEvent(
            Order order,
            DomainEventPublisher<OrderFinishedEvent> orderFinishedEventDomainEventPublisher
    ) {
        super(order);
        this.orderFinishedEventDomainEventPublisher = orderFinishedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderFinishedEventDomainEventPublisher.publish(this);
    }
}
