package com.drink.ordering.system.order.service.messaging.mapper;

import com.drink.ordering.system.infrastructure.kafka.order.avro.model.OrderAvroModel;
import com.drink.ordering.system.infrastructure.kafka.order.avro.model.OrderStatus;
import com.drink.ordering.system.order.service.domain.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMessagingDataMapper {

    public static OrderAvroModel orderCreatedEventToAvroModel(OrderCreatedEvent orderCreatedEvent) {
        return OrderAvroModel.newBuilder()
                .setOrderId(orderCreatedEvent.getOrder().getId().getValue().toString())
                .setDrinkAdditions(orderCreatedEvent.getOrder().getDrinkAdditions().stream().map(Enum::name).collect(Collectors.toList()))
                .setDrinkType(orderCreatedEvent.getOrder().getDrinkType().name())
                .setOrderStatus(OrderStatus.valueOf(
                        orderCreatedEvent.getOrder().getOrderStatus().name()
                ))
                .build();
    }
}
