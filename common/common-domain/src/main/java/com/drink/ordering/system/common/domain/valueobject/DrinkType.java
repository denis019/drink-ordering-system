package com.drink.ordering.system.common.domain.valueobject;

import com.drink.ordering.system.common.domain.exception.DrinkTypeNotFoundException;

public enum DrinkType {
    COFFEE, TEA, CHOCOLATE;

    public static DrinkType fromString(String rawDrinkType) {
        for (DrinkType drinkType : DrinkType.values()) {
            if (drinkType.name().equalsIgnoreCase(rawDrinkType)) {
                return drinkType;
            }
        }

        throw new DrinkTypeNotFoundException("Drink Type not found " + rawDrinkType);
    }
}
