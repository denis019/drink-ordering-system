package com.drink.ordering.system.order.service.messaging.exception;

import org.springframework.messaging.MessagingException;

public class OrderMessagingException extends MessagingException {

    public OrderMessagingException(String message) {
        super(message);
    }
}
