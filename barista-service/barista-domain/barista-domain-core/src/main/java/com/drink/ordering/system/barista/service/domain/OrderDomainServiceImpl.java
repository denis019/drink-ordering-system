package com.drink.ordering.system.barista.service.domain;

import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.barista.service.domain.event.OrderAcceptedEvent;
import com.drink.ordering.system.barista.service.domain.event.OrderFinishedEvent;
import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public OrderAcceptedEvent validateAndInitiateAcceptOrder(
            Order order,
            DomainEventPublisher<OrderAcceptedEvent> orderAcceptedEventDomainEventPublisher
    ) {
        order.validateFromInitToAcceptOrderTransition();
        order.initializeAcceptOrder();

        log.info("Order with id: {} is accepted", order.getId().getValue());
        return new OrderAcceptedEvent(order, orderAcceptedEventDomainEventPublisher);
    }

    @Override
    public OrderFinishedEvent validateAndFinishOrder(
            Order order,
            DomainEventPublisher<OrderFinishedEvent> orderFinishedEventDomainEventPublisher
    ) {
        order.validateFromAcceptedToFinishedTransition();
        order.initializeFinishedOrder();

        log.info("Order with id: {} is finished", order.getId().getValue());
        return new OrderFinishedEvent(order, orderFinishedEventDomainEventPublisher);
    }
}
