package com.drink.ordering.system.barista.service.application.scheduler;

import com.drink.ordering.system.barista.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Barista {

    private final OrderApplicationService orderApplicationService;

    public Barista(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @Scheduled(fixedDelay = 5000)
    public void checkForNewOrder() {
        log.info("checking for new orders");
        orderApplicationService.processAcceptedOrder();
    }
}
