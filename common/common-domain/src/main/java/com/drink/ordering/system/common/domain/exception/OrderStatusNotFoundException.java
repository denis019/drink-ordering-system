package com.drink.ordering.system.common.domain.exception;

public class OrderStatusNotFoundException extends DomainException {

    public OrderStatusNotFoundException(String message) {
        super(message);
    }

    public OrderStatusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
