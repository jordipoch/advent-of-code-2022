package com.challenge.aoc2022.day3;

import com.challenge.aoc2022.day3.exception.FoodSuppliesException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class FoodSuppliesTest {

    @Test
    public void testCalculateSumOfPriorities() throws FoodSuppliesException {
        FoodSupplies foodSupplies = createFoodSupplies();

        Assertions.assertThat(foodSupplies.calculateSumOfPriorities()).isEqualTo(157);
    }

    @Test
    public void testCalculateSumOfGroupBadgePriorities() throws FoodSuppliesException {
        FoodSupplies foodSupplies = createFoodSupplies();

        Assertions.assertThat(foodSupplies.calculateSumOfGroupBadgePriorities()).isEqualTo(70);
    }

    private FoodSupplies createFoodSupplies() throws FoodSuppliesException {
        var foodSupplies = FoodSupplies.Builder.aFoodSupplies()
                .test()
                .items("example_input.txt")
                .build();
        return foodSupplies;
    }


}