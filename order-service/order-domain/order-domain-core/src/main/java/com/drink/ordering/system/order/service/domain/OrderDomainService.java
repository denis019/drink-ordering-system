package com.drink.ordering.system.order.service.domain;

import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;
import com.drink.ordering.system.order.service.domain.entity.Order;
import com.drink.ordering.system.order.service.domain.event.OrderCreatedEvent;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(
            Order order,
            DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher
    );
}
