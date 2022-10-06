package com.drink.ordering.system.order.service.domain.dto.find;

import com.drink.ordering.system.common.domain.valueobject.DrinkAddition;
import com.drink.ordering.system.common.domain.valueobject.DrinkType;
import com.drink.ordering.system.common.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class FindOrderResponse {

    @NotNull
    private final UUID orderId;

    @NotNull
    private final DrinkType drinkType;

    @NotNull
    private final List<DrinkAddition> drinkAdditions;

    @NotNull
    private final OrderStatus orderStatus;

    @NotNull
    private final Instant orderCreatedAt;
}
