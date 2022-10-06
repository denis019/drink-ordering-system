package com.drink.ordering.system.common.domain.exception;

public class DrinkAdditionNotFoundException extends DomainException {

    public DrinkAdditionNotFoundException(String message) {
        super(message);
    }

    public DrinkAdditionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
