package com.drink.ordering.system.order.service.messaging.exception;

public class KafkaPublishMessageException extends OrderMessagingException {

    public KafkaPublishMessageException(String message) {
        super(message);
    }
}
