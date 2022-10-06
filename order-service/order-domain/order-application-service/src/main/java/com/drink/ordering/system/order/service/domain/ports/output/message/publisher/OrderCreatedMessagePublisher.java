package com.drink.ordering.system.order.service.domain.ports.output.message.publisher;

import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;
import com.drink.ordering.system.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
