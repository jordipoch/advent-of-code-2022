package com.challenge.aoc2022.day3;

import com.challenge.aoc2022.day2.Day2;
import com.challenge.aoc2022.exception.ExecutionException;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Test {

    @Test
    public void testRunPart1() throws ExecutionException {
        Day3 day3 = new Day3();
        assertThat(day3.runPart1()).isEqualTo(7_821);
        assertThat(day3.runPart2()).isEqualTo(2_752);
    }
}