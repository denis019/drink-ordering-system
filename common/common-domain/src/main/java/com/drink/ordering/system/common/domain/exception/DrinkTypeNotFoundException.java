package com.drink.ordering.system.common.domain.exception;

public class DrinkTypeNotFoundException extends DomainException {

    public DrinkTypeNotFoundException(String message) {
        super(message);
    }

    public DrinkTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
