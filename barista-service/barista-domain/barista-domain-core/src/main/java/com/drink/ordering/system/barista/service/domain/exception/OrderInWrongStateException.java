package com.drink.ordering.system.barista.service.domain.exception;

import com.drink.ordering.system.common.domain.exception.DomainException;

public class OrderInWrongStateException extends DomainException {
    public OrderInWrongStateException(String message) {
        super(message);
    }
}
