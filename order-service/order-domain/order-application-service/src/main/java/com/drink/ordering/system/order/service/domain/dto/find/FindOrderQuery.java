package com.drink.ordering.system.order.service.domain.dto.find;

import com.drink.ordering.system.common.domain.valueobject.OrderId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class FindOrderQuery {
    @NotNull
    private final OrderId orderId;
}
