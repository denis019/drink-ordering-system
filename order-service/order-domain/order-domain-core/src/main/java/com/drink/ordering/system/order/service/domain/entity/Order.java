package com.drink.ordering.system.order.service.domain.entity;

import com.drink.ordering.system.common.domain.entity.BaseEntity;
import com.drink.ordering.system.common.domain.valueobject.DrinkAddition;
import com.drink.ordering.system.common.domain.valueobject.DrinkType;
import com.drink.ordering.system.common.domain.valueobject.OrderCreatedAt;
import com.drink.ordering.system.common.domain.valueobject.OrderId;
import com.drink.ordering.system.common.domain.valueobject.OrderStatus;
import com.drink.ordering.system.order.service.domain.exception.OrderDomainException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order extends BaseEntity<OrderId> {
    private final DrinkType drinkType;
    private final List<DrinkAddition> drinkAdditions;
    private final OrderCreatedAt createdAt;

    private OrderStatus orderStatus;

    public static final String LIST_DELIMITER = ",";

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        orderStatus = OrderStatus.PLACED;
    }

    public void validateOrder() {
        validateInitialOrder();
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException("Order is not in correct state for initialization!");
        }
    }

    private Order(Builder builder) {
        super.setId(builder.orderId);
        drinkType = builder.drinkType;
        createdAt = builder.createdAt;
        drinkAdditions = builder.drinkAdditions;
        orderStatus = builder.orderStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public OrderCreatedAt getCreatedAt() {
        return createdAt;
    }

    public List<DrinkAddition> getDrinkAdditions() {
        if(drinkAdditions == null) {
            return Collections.emptyList();
        }

        return drinkAdditions;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public static final class Builder {
        private OrderId orderId;
        private DrinkType drinkType;
        private OrderCreatedAt createdAt;
        private List<DrinkAddition> drinkAdditions;
        private OrderStatus orderStatus;

        private Builder() {
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder drinkType(DrinkType val) {
            drinkType = val;
            return this;
        }

        public Builder createdAt(OrderCreatedAt val) {
            createdAt = val;
            return this;
        }

        public Builder drinkAdditions(List<DrinkAddition> val) {
            drinkAdditions = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
