package com.drink.ordering.system.common.domain.valueobject;

import com.drink.ordering.system.common.domain.exception.DrinkAdditionNotFoundException;

public enum DrinkAddition {
    SUGAR, MILK;

    public static DrinkAddition fromString(String rawDrinkAddition) {
        for (DrinkAddition drinkAddition : DrinkAddition.values()) {
            if (drinkAddition.name().equalsIgnoreCase(rawDrinkAddition)) {
                return drinkAddition;
            }
        }

        throw new DrinkAdditionNotFoundException("Drink Type not found " + rawDrinkAddition);
    }
}
