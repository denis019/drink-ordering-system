package com.drink.ordering.system.order.service.messaging.publisher.kafka;

import com.drink.ordering.system.infrastructure.kafka.order.avro.model.OrderAvroModel;
import com.drink.ordering.system.infrastructure.kafka.producer.KafkaMessageHelper;
import com.drink.ordering.system.infrastructure.kafka.producer.service.KafkaProducer;
import com.drink.ordering.system.order.service.domain.config.OrderServiceConfigData;
import com.drink.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.drink.ordering.system.order.service.domain.ports.output.message.publisher.OrderCreatedMessagePublisher;
import com.drink.ordering.system.order.service.messaging.exception.KafkaPublishMessageException;
import com.drink.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreatedKafkaMessagePublisher implements OrderCreatedMessagePublisher {

    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaProducer<String, OrderAvroModel> kafkaProducer;
    private final KafkaMessageHelper orderKafkaMessageHelper;

    public OrderCreatedKafkaMessagePublisher(
            OrderServiceConfigData orderServiceConfigData,
            KafkaProducer<String, OrderAvroModel> kafkaProducer,
            KafkaMessageHelper kafkaMessageHelper
    ) {
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.orderKafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();
        log.info("Received OrderCreatedEvent for order id: {}", orderId);

        try {
            OrderAvroModel orderAvroModel = OrderMessagingDataMapper.orderCreatedEventToAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getOrderTopicName(),
                    orderId,
                    orderAvroModel,
                    orderKafkaMessageHelper
                            .getKafkaCallback(orderServiceConfigData.getOrderTopicName(),
                                    orderAvroModel,
                                    orderId,
                                    "OrderAvroModel"));

            log.info("PaymentRequestAvroModel sent to Kafka for order id: {}", orderAvroModel.getOrderId());
        } catch (Exception e) {
            log.error("Error while publishing OrderPlacedEvent" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());

            throw new KafkaPublishMessageException("Error while publishing OrderPlacedEvent");
        }
    }
}
