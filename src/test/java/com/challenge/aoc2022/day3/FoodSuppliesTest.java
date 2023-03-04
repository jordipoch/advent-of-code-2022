package com.challenge.aoc2022.day3;

import com.challenge.aoc2022.day3.exception.FoodSuppliesException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FoodSuppliesTest {

    @Test
    public void testCalculateSumOfPriorities() throws FoodSuppliesException {
        var foodSupplies = FoodSupplies.Builder.aFoodSupplies()
                .test()
                .items("example_input.txt")
                .build();

        Assertions.assertThat(foodSupplies.calculateSumOfPriorities()).isEqualTo(157);
    }
}