package com.drink.ordering.system.barista.service.domain.ports.output.message.publisher;

import com.drink.ordering.system.barista.service.domain.event.OrderAcceptedEvent;
import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;

public interface OrderAcceptedMessagePublisher extends DomainEventPublisher<OrderAcceptedEvent> {
}
