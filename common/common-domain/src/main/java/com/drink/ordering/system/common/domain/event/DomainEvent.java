package com.drink.ordering.system.common.domain.event;

public interface DomainEvent<T> {
    void fire();
}
