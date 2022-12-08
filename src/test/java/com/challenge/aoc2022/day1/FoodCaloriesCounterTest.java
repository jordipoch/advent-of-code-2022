package com.challenge.aoc2022.day1;

import com.challenge.aoc2022.day1.exception.FoodCaloriesCounterException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.challenge.aoc2022.day1.FoodCaloriesCounter.Builder.fromList;

public class FoodCaloriesCounterTest {
    private static final String[] SIMPLE_LIST = {
            "300", "400",
            "",
            "200", "700", "400"
    };

    private static final String[] EXAMPLE_LIST = {
            "1000", "2000", "3000",
            "",
            "4000",
            "",
            "5000", "6000",
            "",
            "7000", "8000", "9000",
            "",
            "10000"
    };

    @Test
    public void testCalculateMaxWithSingleBackPack() {
        FoodCaloriesCounter counter = fromList("300", "400");
        var max = counter.calculateMax();

        Assertions.assertThat(max).isEqualTo(700);
    }

    @Test
    public void testCalculateMaxWithMultipleBackPack() {
        FoodCaloriesCounter counter = fromList(SIMPLE_LIST);
        var max = counter.calculateMax();
        Assertions.assertThat(max).isEqualTo(1300);
    }

    @Test
    public void testCalculateMaxWithExampleList() {
        FoodCaloriesCounter counter = fromList(EXAMPLE_LIST);
        var max = counter.calculateMax();

        Assertions.assertThat(max).isEqualTo(24_000);
    }

    @Test
    public void testCalculateTop1WithSimpleList() throws FoodCaloriesCounterException {
        var counter = fromList(SIMPLE_LIST);
        Assertions.assertThat(counter.calculateTopNSum(1)).isEqualTo(1300);
    }

    @Test
    public void testCalculateTop1WithExampleList() throws FoodCaloriesCounterException {
        var counter = fromList(EXAMPLE_LIST);
        Assertions.assertThat(counter.calculateTopNSum(3)).isEqualTo(45_000);
    }

    @Test(expectedExceptions = FoodCaloriesCounterException.class)
    public void testCalculateTopNErrorNTooLarge() throws FoodCaloriesCounterException {
        fromList(SIMPLE_LIST).calculateTopNSum(3);
    }

    @Test(expectedExceptions = FoodCaloriesCounterException.class)
    public void testCalculateTopNErrorNEquals0() throws FoodCaloriesCounterException {
        fromList(SIMPLE_LIST).calculateTopNSum(0);
    }
}