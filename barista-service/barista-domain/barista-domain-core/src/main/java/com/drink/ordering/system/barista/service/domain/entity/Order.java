package com.drink.ordering.system.barista.service.domain.entity;

import com.drink.ordering.system.barista.service.domain.exception.OrderInWrongStateException;
import com.drink.ordering.system.common.domain.entity.BaseEntity;
import com.drink.ordering.system.common.domain.valueobject.OrderId;
import com.drink.ordering.system.common.domain.valueobject.OrderPreparationStatus;

public class Order extends BaseEntity<OrderId> {
    private OrderPreparationStatus orderPreparationStatus;

    public void initializeAcceptOrder() {
        this.orderPreparationStatus = OrderPreparationStatus.ORDER_ACCEPTED;
    }

    public void initializeFinishedOrder() {
        this.orderPreparationStatus = OrderPreparationStatus.ORDER_FINISHED;
    }

    public void validateFromInitToAcceptOrderTransition() {
        if (orderPreparationStatus != null || getId() == null) {
            throw new OrderInWrongStateException("Order is not in correct state to perform FromInitToAcceptOrderTransition!");
        }
    }

    public void validateFromAcceptedToFinishedTransition() {
        if (orderPreparationStatus != OrderPreparationStatus.ORDER_ACCEPTED || getId() == null) {
            throw new OrderInWrongStateException("Order is not in correct state to perform FromAcceptedToFinishedTransition!");
        }
    }

    private Order(Builder builder) {
        super.setId(builder.orderId);
        orderPreparationStatus = builder.orderPreparationStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public OrderPreparationStatus getOrderPreparationStatus() {
        return orderPreparationStatus;
    }

    public static final class Builder {
        private OrderId orderId;
        private OrderPreparationStatus orderPreparationStatus;

        private Builder() {
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder orderPreparationStatus(OrderPreparationStatus val) {
            orderPreparationStatus = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
