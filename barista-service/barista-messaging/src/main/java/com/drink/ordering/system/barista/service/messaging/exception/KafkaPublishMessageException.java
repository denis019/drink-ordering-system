package com.drink.ordering.system.barista.service.messaging.exception;

public class KafkaPublishMessageException extends OrderMessagingException {

    public KafkaPublishMessageException(String message) {
        super(message);
    }
}
