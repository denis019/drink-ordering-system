package com.drink.ordering.system.barista.service.domain.ports.output.message.publisher;

import com.drink.ordering.system.barista.service.domain.event.OrderFinishedEvent;
import com.drink.ordering.system.common.domain.event.publisher.DomainEventPublisher;

public interface OrderFinishedMessagePublisher extends DomainEventPublisher<OrderFinishedEvent> {
}
