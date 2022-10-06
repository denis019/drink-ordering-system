package com.drink.ordering.system.order.service.domain.dto.create;

import com.drink.ordering.system.common.domain.valueobject.DrinkAddition;
import com.drink.ordering.system.common.domain.valueobject.DrinkType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
    @NotNull
    private final DrinkType drinkType;

    private final List<DrinkAddition> drinkAdditions;
}
