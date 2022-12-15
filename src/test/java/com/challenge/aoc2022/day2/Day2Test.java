package com.challenge.aoc2022.day2;

import com.challenge.aoc2022.exception.ExecutionException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day2Test {

    @Test
    public void testRunPart1() throws ExecutionException {
        Day2 day2 = new Day2();
        assertThat(day2.runPart1()).isEqualTo(11_475);
        assertThat(day2.runPart2()).isEqualTo(16_862);
    }
}