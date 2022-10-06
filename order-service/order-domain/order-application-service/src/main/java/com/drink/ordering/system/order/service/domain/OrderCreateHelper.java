package com.drink.ordering.system.order.service.domain;

import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.drink.ordering.system.order.service.domain.entity.Order;
import com.drink.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.drink.ordering.system.order.service.domain.exception.OrderDomainException;
import com.drink.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.drink.ordering.system.order.service.domain.ports.output.message.publisher.OrderCreatedMessagePublisher;
import com.drink.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderCreateHelper {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final OrderCreatedMessagePublisher orderCreatedEventDomainEventPublisher;

    public OrderCreateHelper(
            OrderDomainService orderDomainService,
            OrderRepository orderRepository,
            OrderCreatedMessagePublisher orderCreatedEventDomainEventPublisher
    ) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
    }

    public OrderCreatedEvent createOrder(CreateOrderCommand createOrderCommand) {
        Order order = OrderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent =  orderDomainService.validateAndInitiateOrder(
                order,
                orderCreatedEventDomainEventPublisher
        );

        saveOrder(order);

        return orderCreatedEvent;
    }

    @Transactional
    public void saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
    }
}
