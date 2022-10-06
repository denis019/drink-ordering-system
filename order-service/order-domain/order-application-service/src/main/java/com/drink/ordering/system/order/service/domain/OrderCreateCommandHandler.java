package com.drink.ordering.system.order.service.domain;

import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.drink.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.drink.ordering.system.order.service.domain.mapper.OrderDataMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateCommandHandler {

    private final OrderCreateHelper orderCreateHelper;

    public OrderCreateCommandHandler(
            OrderCreateHelper orderCreateHelper
    ) {
        this.orderCreateHelper = orderCreateHelper;
    }

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.createOrder(createOrderCommand);
        orderCreatedEvent.fire();

        return OrderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
    }
}
