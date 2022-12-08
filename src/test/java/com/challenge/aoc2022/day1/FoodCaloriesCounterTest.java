package com.challenge.aoc2022.day1;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static com.challenge.aoc2022.day1.FoodCaloriesCounter.Builder.fromList;

public class FoodCaloriesCounterTest {

    @Test
    public void testSingleBackPack() {
        FoodCaloriesCounter counter = fromList("300", "400");
        var max = counter.calculateMaxCaloriesCarriedByAnElf();

        Assertions.assertThat(max).isEqualTo(700);
    }

    @Test
    public void testMultipleBackPack() {
        FoodCaloriesCounter counter = fromList(
                "300", "400",
                "",
                "200", "700", "400");
        var max = counter.calculateMaxCaloriesCarriedByAnElf();

        Assertions.assertThat(max).isEqualTo(1300);
    }

    @Test
    public void testWithExample() {
        FoodCaloriesCounter counter = fromList(
                "1000", "2000", "3000",
                "",
                "4000",
                "",
                "5000", "6000",
                "",
                "7000", "8000", "9000",
                "",
                "10000");
        var max = counter.calculateMaxCaloriesCarriedByAnElf();

        Assertions.assertThat(max).isEqualTo(24_000);
    }
}