package com.drink.ordering.system.barista.service.domain;

import com.drink.ordering.system.barista.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final ProcessOrderCommandHandler processOrderCommandHandler;

    public OrderApplicationServiceImpl(
            ProcessOrderCommandHandler processOrderCommandHandler
    ) {
        this.processOrderCommandHandler = processOrderCommandHandler;
    }

    @Override
    public void processAcceptedOrder() {
        processOrderCommandHandler.handle();
    }
}
