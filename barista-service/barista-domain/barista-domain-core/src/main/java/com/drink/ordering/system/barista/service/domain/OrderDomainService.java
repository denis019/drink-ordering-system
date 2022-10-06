package com.drink.ordering.system.barista.service.domain;

import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.barista.service.domain.event.OrderAcceptedEvent;
import com.drink.ordering.system.barista.service.domain.event.OrderFinishedEvent;
import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;

public interface OrderDomainService {

    OrderAcceptedEvent validateAndInitiateAcceptOrder(
            Order order,
            DomainEventPublisher<OrderAcceptedEvent> orderCreatedEventDomainEventPublisher
    );

    OrderFinishedEvent validateAndFinishOrder(
            Order order,
            DomainEventPublisher<OrderFinishedEvent> orderCreatedEventDomainEventPublisher
    );
}
