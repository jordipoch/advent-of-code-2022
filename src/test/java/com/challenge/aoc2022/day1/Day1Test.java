package com.challenge.aoc2022.day1;

import com.challenge.aoc2022.exception.ExecutionException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class Day1Test {
    @Test
    public void testPart1() throws ExecutionException {
        Assertions.assertThat(new Day1().runPart1()).isEqualTo(71_502);
    }

    @Test
    public void testPart2() throws ExecutionException {
        Assertions.assertThat(new Day1().runPart2()).isEqualTo(208_191);
    }
}