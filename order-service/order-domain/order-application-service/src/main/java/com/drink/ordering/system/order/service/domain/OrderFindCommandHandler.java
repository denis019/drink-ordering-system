package com.drink.ordering.system.order.service.domain;

import com.drink.ordering.system.order.service.domain.dto.find.FindOrderQuery;
import com.drink.ordering.system.order.service.domain.dto.find.FindOrderResponse;
import com.drink.ordering.system.order.service.domain.entity.Order;
import com.drink.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.drink.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderFindCommandHandler {

    private final OrderRepository orderRepository;

    public OrderFindCommandHandler(
            OrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    public FindOrderResponse findOrder(FindOrderQuery findOrderQuery) {
        Order order = orderRepository.findOneByIdOrThrowException(findOrderQuery.getOrderId());
        return OrderDataMapper.orderToFindOrderResponse(order);
    }
}
