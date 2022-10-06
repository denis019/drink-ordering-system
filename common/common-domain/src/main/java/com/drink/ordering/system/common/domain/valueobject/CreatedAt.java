package com.drink.ordering.system.common.domain.valueobject;

import java.util.Objects;

public abstract class CreatedAt<T> {
    private final T value;

    protected CreatedAt(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedAt<?> baseId = (CreatedAt<?>) o;
        return value.equals(baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
