package com.drink.ordering.system.barista.service.domain.ports.output.repository;

import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.common.domain.valueobject.OrderId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    boolean isOrderAlreadyProcessed(OrderId orderId);

    Optional<Order> findFirstWithStatusAcceptedOrderedByCreatedAtAsc();
}
