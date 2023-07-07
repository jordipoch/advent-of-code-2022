package com.challenge.aoc2022.day4;

import com.challenge.aoc2022.exception.ExecutionException;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day4Test {

    @Test
    public void testRunPart1() throws ExecutionException {
        Day4 day4 = new Day4();
        assertThat(day4.runPart1()).isEqualTo(509);
    }

    @Test
    public void testRunPart2() throws ExecutionException {
        Day4 day4 = new Day4();
        assertThat(day4.runPart2()).isEqualTo(870);
    }
}