package com.drink.ordering.system.order.service.domain;

import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.drink.ordering.system.order.service.domain.dto.find.FindOrderQuery;
import com.drink.ordering.system.order.service.domain.dto.find.FindOrderResponse;
import com.drink.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderFindCommandHandler orderFindCommandHandler;

    public OrderApplicationServiceImpl(
            OrderCreateCommandHandler orderCreateCommandHandler,
            OrderFindCommandHandler orderFindCommandHandler
    ) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderFindCommandHandler = orderFindCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public FindOrderResponse findOrder(FindOrderQuery findOrderQuery) {
        return orderFindCommandHandler.findOrder(findOrderQuery);
    }
}
