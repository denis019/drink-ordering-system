package com.drink.ordering.system.common.domain.valueobject;

import java.time.Instant;

public class OrderCreatedAt extends CreatedAt<Instant> {

    public OrderCreatedAt(Instant value) {
        super(value);
    }
}
