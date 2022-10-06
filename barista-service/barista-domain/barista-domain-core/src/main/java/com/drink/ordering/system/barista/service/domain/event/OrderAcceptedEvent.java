package com.drink.ordering.system.barista.service.domain.event;

import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;

public class OrderAcceptedEvent extends OrderEvent {

    private final DomainEventPublisher<OrderAcceptedEvent> orderAcceptedEventDomainEventPublisher;

    public OrderAcceptedEvent(
            Order order,
            DomainEventPublisher<OrderAcceptedEvent> orderAcceptedEventDomainEventPublisher
    ) {
        super(order);
        this.orderAcceptedEventDomainEventPublisher = orderAcceptedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderAcceptedEventDomainEventPublisher.publish(this);
    }
}
