package com.drink.ordering.system.order.service.dataaccess.order.adapter;

import com.drink.ordering.system.common.domain.valueobject.OrderId;
import com.drink.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.drink.ordering.system.order.service.dataaccess.order.mapper.OrderDataAccessMapper;
import com.drink.ordering.system.order.service.dataaccess.order.repository.OrderJpaRepository;
import com.drink.ordering.system.order.service.domain.entity.Order;
import com.drink.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.drink.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderDataAccessMapper.orderToOrderEntity(order);
        orderJpaRepository.save(orderEntity);

        return OrderDataAccessMapper.orderEntityToOrder(orderEntity);
    }

    @Override
    public Order findOneByIdOrThrowException(OrderId orderId) {
        return orderJpaRepository
                .findOneById(orderId.getValue())
                .map(OrderDataAccessMapper::orderEntityToOrder)
                .orElseThrow(() -> new OrderNotFoundException("Could not find order with order id: " + orderId.getValue()));
    }
}
