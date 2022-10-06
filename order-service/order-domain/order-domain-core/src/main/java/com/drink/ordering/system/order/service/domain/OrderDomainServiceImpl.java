package com.drink.ordering.system.order.service.domain;

import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;
import com.drink.ordering.system.order.service.domain.entity.Order;
import com.drink.ordering.system.order.service.domain.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(
            Order order,
            DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher
    ) {
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, orderCreatedEventDomainEventPublisher);
    }
}
