package com.drink.ordering.system.barista.service.messaging.mapper;

import com.drink.ordering.system.barista.service.domain.dto.receive.AcceptOrderDto;
import com.drink.ordering.system.barista.service.domain.entity.Order;
import com.drink.ordering.system.barista.service.domain.event.OrderAcceptedEvent;
import com.drink.ordering.system.barista.service.domain.event.OrderFinishedEvent;
import com.drink.ordering.system.common.domain.valueobject.OrderId;
import com.drink.ordering.system.infrastructure.kafka.order.avro.model.BaristaAvroModel;
import com.drink.ordering.system.infrastructure.kafka.order.avro.model.OrderAvroModel;
import com.drink.ordering.system.infrastructure.kafka.order.avro.model.OrderPreparationStatus;

import java.util.UUID;

public class BaristaDataMapper {

    public static AcceptOrderDto orderAvroModelToReceiveOrderDto(OrderAvroModel orderAvroModel) {
        return AcceptOrderDto.builder()
                .orderId(new OrderId(UUID.fromString(orderAvroModel.getOrderId())))
                .build();
    }

    public static BaristaAvroModel orderAcceptedEventToBaristaAvroModel(OrderAcceptedEvent orderAcceptedEvent) {
        Order order = orderAcceptedEvent.getOrder();

        return BaristaAvroModel.newBuilder()
                .setOrderId(order.getId().getValue().toString())
                .setOrderPreparationStatus(OrderPreparationStatus.valueOf(
                        order.getOrderPreparationStatus().name()))
                .build();
    }

    public static BaristaAvroModel orderFinishedEventToBaristaAvroModel(OrderFinishedEvent orderFinishedEvent) {
        Order order = orderFinishedEvent.getOrder();

        return BaristaAvroModel.newBuilder()
                .setOrderId(order.getId().getValue().toString())
                .setOrderPreparationStatus(OrderPreparationStatus.valueOf(
                        order.getOrderPreparationStatus().name()))
                .build();
    }
}
