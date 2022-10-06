package com.drink.ordering.system.barista.service.messaging.publisher.kafka;

import com.drink.ordering.system.barista.service.domain.config.BaristaServiceConfigData;
import com.drink.ordering.system.barista.service.domain.event.OrderFinishedEvent;
import com.drink.ordering.system.barista.service.domain.ports.output.message.publisher.OrderFinishedMessagePublisher;
import com.drink.ordering.system.barista.service.messaging.exception.KafkaPublishMessageException;
import com.drink.ordering.system.barista.service.messaging.mapper.BaristaDataMapper;
import com.drink.ordering.system.infrastructure.kafka.order.avro.model.BaristaAvroModel;
import com.drink.ordering.system.infrastructure.kafka.producer.KafkaMessageHelper;
import com.drink.ordering.system.infrastructure.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderFinishedKafkaMessagePublisher implements OrderFinishedMessagePublisher {

    private final BaristaServiceConfigData baristaServiceConfigData;
    private final KafkaProducer<String, BaristaAvroModel> kafkaProducer;
    private final KafkaMessageHelper orderKafkaMessageHelper;

    public OrderFinishedKafkaMessagePublisher(
            BaristaServiceConfigData baristaServiceConfigData,
            KafkaProducer<String, BaristaAvroModel> kafkaProducer,
            KafkaMessageHelper kafkaMessageHelper
    ) {
        this.baristaServiceConfigData = baristaServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.orderKafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(OrderFinishedEvent orderFinishedEvent) {
        String orderId = orderFinishedEvent.getOrder().getId().getValue().toString();
        log.info("Order finished, order id: {}", orderId);

        try {
            BaristaAvroModel baristaAvroModel = BaristaDataMapper.orderFinishedEventToBaristaAvroModel(orderFinishedEvent);

            kafkaProducer.send(baristaServiceConfigData.getBaristaTopicName(),
                    orderId,
                    baristaAvroModel,
                    orderKafkaMessageHelper
                            .getKafkaCallback(baristaServiceConfigData.getBaristaTopicName(),
                                    baristaAvroModel,
                                    orderId,
                                    "BaristaAvroModel"
                            ));

            log.info("BaristaAvroModel sent to Kafka for order id: {}", baristaAvroModel.getOrderId());
        } catch (Exception e) {
            log.error("Error while publishing OrderFinishedEvent" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());

            throw new KafkaPublishMessageException("Error while publishing OrderFinishedEvent");
        }
    }
}
