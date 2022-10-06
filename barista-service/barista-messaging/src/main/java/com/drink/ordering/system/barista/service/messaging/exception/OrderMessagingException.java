package com.drink.ordering.system.barista.service.messaging.exception;

import org.springframework.messaging.MessagingException;

public class OrderMessagingException extends MessagingException {

    public OrderMessagingException(String message) {
        super(message);
    }
}
