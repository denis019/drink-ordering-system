package com.drink.ordering.system.barista.service.domain;

import com.drink.ordering.system.barista.service.domain.event.OrderFinishedEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProcessOrderCommandHandler {

    private final OrderProcessHelper orderProcessHelper;

    public ProcessOrderCommandHandler(
            OrderProcessHelper orderProcessHelper
    ) {
        this.orderProcessHelper = orderProcessHelper;
    }

    public void handle() {
        Optional<OrderFinishedEvent> orderFinishedEvent = orderProcessHelper.processFirstInQueueAcceptedOrder();
        orderFinishedEvent.ifPresent(OrderFinishedEvent::fire);
    }
}
