package com.drink.ordering.system.barista.service.domain;

import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.barista.service.domain.event.OrderFinishedEvent;
import com.drink.ordering.system.barista.service.domain.exception.OrderSaveException;
import com.drink.ordering.system.barista.service.domain.ports.output.message.publisher.OrderFinishedMessagePublisher;
import com.drink.ordering.system.barista.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class OrderProcessHelper {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final OrderFinishedMessagePublisher orderFinishedMessagePublisher;

    public OrderProcessHelper(
            OrderDomainService orderDomainService,
            OrderRepository orderRepository,
            OrderFinishedMessagePublisher orderFinishedMessagePublisher
    ) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.orderFinishedMessagePublisher = orderFinishedMessagePublisher;
    }

    @Transactional
    public Optional<OrderFinishedEvent> processFirstInQueueAcceptedOrder() {
        Optional<Order> order = orderRepository.findFirstWithStatusAcceptedOrderedByCreatedAtAsc();

        if(order.isEmpty()) {
            log.info("No orders to process");
            return Optional.empty();
        }

        OrderFinishedEvent orderFinishedEvent = orderDomainService.validateAndFinishOrder(
                order.get(),
                orderFinishedMessagePublisher
        );

        saveOrder(order.get());

        return Optional.of(orderFinishedEvent);
    }

    private void saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order: {}", order.getId().getValue());
            throw new OrderSaveException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
    }
}
