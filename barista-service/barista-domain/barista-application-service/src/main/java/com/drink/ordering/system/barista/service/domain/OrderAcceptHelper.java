package com.drink.ordering.system.barista.service.domain;

import com.drink.ordering.system.barista.service.domain.dto.receive.AcceptOrderDto;
import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.barista.service.domain.event.OrderAcceptedEvent;
import com.drink.ordering.system.barista.service.domain.exception.OrderAlreadyAcceptedException;
import com.drink.ordering.system.barista.service.domain.exception.OrderSaveException;
import com.drink.ordering.system.barista.service.domain.mapper.OrderDataMapper;
import com.drink.ordering.system.barista.service.domain.ports.output.message.publisher.OrderAcceptedMessagePublisher;
import com.drink.ordering.system.barista.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderAcceptHelper {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final OrderAcceptedMessagePublisher orderAcceptedMessagePublisher;

    public OrderAcceptHelper(
            OrderDomainService orderDomainService,
            OrderRepository orderRepository,
            OrderAcceptedMessagePublisher orderAcceptedMessagePublisher

    ) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.orderAcceptedMessagePublisher = orderAcceptedMessagePublisher;
    }

    @Transactional
    public void acceptOrder(AcceptOrderDto acceptOrderDto) {
        Order order = OrderDataMapper.acceptOrderDtoToOrder(acceptOrderDto);
        OrderAcceptedEvent orderAcceptedEvent = orderDomainService.validateAndInitiateAcceptOrder(
                order,
                orderAcceptedMessagePublisher
        );

        if (orderRepository.isOrderAlreadyProcessed(order.getId())) {
            log.error("Could not accept order, order already processed: {}", order.getId().getValue());
            throw new OrderAlreadyAcceptedException("Order already processed");
        }

        saveOrder(order);

        orderAcceptedEvent.fire();
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
