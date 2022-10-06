package com.drink.ordering.system.order.service.domain.ports.input.service;

import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.drink.ordering.system.order.service.domain.dto.find.FindOrderQuery;
import com.drink.ordering.system.order.service.domain.dto.find.FindOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    FindOrderResponse findOrder(@Valid FindOrderQuery findOrderQuery);
}
