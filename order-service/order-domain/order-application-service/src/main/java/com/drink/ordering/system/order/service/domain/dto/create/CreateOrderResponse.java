package com.drink.ordering.system.order.service.domain.dto.create;

import com.drink.ordering.system.common.domain.valueobject.OrderId;
import com.drink.ordering.system.common.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    @NotNull
    private final OrderId orderId;
    @NotNull
    private final OrderStatus orderStatus;
}
