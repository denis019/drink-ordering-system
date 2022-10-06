package com.drink.ordering.system.barista.service.domain.exception;

import com.drink.ordering.system.common.domain.exception.DomainException;

public class OrderAlreadyAcceptedException extends DomainException {
    public OrderAlreadyAcceptedException(String message) {
        super(message);
    }
}
