package com.drink.ordering.system.barista.service.domain.exception;

import com.drink.ordering.system.common.domain.exception.DomainException;

public class OrderSaveException extends DomainException {
    public OrderSaveException(String message) {
        super(message);
    }
}
