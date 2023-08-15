package com.challenge.aoc2022.day6;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Iterator;

public abstract class MarkerPositionCalculatorTest {
    protected MarkerPositionCalculator markerPositionCalculator;
    @DataProvider(name = "simple tests")
    protected Iterator<Object[]> getSimpleTestsData() {
        return Arrays.asList(new Object[][] {
                {"abc", 0},
                {"abca", 0},
                {"abcb", 0},
                {"abcc", 0},
                {"abcd", 4},
                {"abcbcda", 7},
                {"abcbcdaf", 7}
        }).iterator();
    }

    @DataProvider(name = "complex tests")
    protected Iterator<Object[]> getComplexTestsData() {
        return Arrays.asList(new Object[][] {
                {"bvwbjplbgvbhsrlpgdmjqwftvncz", 5},
                {"nppdvjthqldpwncqszvftbrmjlhg", 6},
                {"nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10},
                {"zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11},
        }).iterator();
    }
}
