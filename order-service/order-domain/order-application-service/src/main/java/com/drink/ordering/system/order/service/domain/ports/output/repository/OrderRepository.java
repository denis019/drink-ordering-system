package com.drink.ordering.system.order.service.domain.ports.output.repository;

import com.drink.ordering.system.common.domain.valueobject.OrderId;
import com.drink.ordering.system.order.service.domain.entity.Order;

public interface OrderRepository {

    Order save(Order order);

    Order findOneByIdOrThrowException(OrderId orderId);
}
