package com.drink.ordering.system.barista.service.dataaccess.order.adapter;

import com.drink.ordering.system.barista.service.dataaccess.order.entity.OrderEntity;
import com.drink.ordering.system.barista.service.dataaccess.order.mapper.OrderDataAccessMapper;
import com.drink.ordering.system.barista.service.dataaccess.order.repository.OrderJpaRepository;
import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.barista.service.domain.ports.output.repository.OrderRepository;
import com.drink.ordering.system.common.domain.valueobject.OrderId;
import com.drink.ordering.system.common.domain.valueobject.OrderPreparationStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public boolean isOrderAlreadyProcessed(OrderId orderId) {
        return orderJpaRepository.existsById(orderId.getValue());
    }

    @Override
    public Optional<Order> findFirstWithStatusAcceptedOrderedByCreatedAtAsc() {
        Optional<OrderEntity> orderEntity = orderJpaRepository.findFirstByOrderPreparationStatusOrderByCreatedAtAsc(OrderPreparationStatus.ORDER_ACCEPTED);

        if(orderEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(OrderDataAccessMapper.orderEntityToOrder(orderEntity.get()));
    }
}
