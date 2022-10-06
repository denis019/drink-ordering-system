package com.drink.ordering.system.barista.service.domain.dto.receive;

import com.drink.ordering.system.common.domain.valueobject.OrderId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class AcceptOrderDto {
    private final OrderId orderId;
}
