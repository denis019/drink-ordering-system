package com.drink.ordering.system.barista.service.messaging.listener;


import com.drink.ordering.system.barista.service.domain.ports.input.message.listener.OrderMessageListener;
import com.drink.ordering.system.barista.service.messaging.mapper.BaristaDataMapper;
import com.drink.ordering.system.infrastructure.kafka.consumer.KafkaConsumer;
import com.drink.ordering.system.infrastructure.kafka.order.avro.model.OrderAvroModel;
import com.drink.ordering.system.infrastructure.kafka.order.avro.model.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Slf4j
@Component
public class OrderKafkaListener implements KafkaConsumer<OrderAvroModel> {
    private final OrderMessageListener orderMessageListener;

    public OrderKafkaListener(OrderMessageListener orderMessageListener) {
        this.orderMessageListener = orderMessageListener;
    }

    @Override
    @KafkaListener(
            id = "barista-service-#{T(java.util.UUID).randomUUID().toString()}",
            topics = "${order-service.order-topic-name}"
    )
    public void receive(@Payload List<OrderAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Instant createdAt,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of orders received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(orderAvroModel -> {
            if (OrderStatus.PLACED == orderAvroModel.getOrderStatus()) {
                log.info("Processing order with status: {}, and id: {}",
                        orderAvroModel.getOrderStatus(),
                        orderAvroModel.getOrderId()
                );

                orderMessageListener.acceptOrder(BaristaDataMapper.orderAvroModelToReceiveOrderDto(orderAvroModel));
            }
        });
    }
}
