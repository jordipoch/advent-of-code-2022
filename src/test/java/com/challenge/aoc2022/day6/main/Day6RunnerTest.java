package com.challenge.aoc2022.day6.main;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class Day6RunnerTest {
    @Test
    public void testRunPart1() {
        var runner = new Day6Runner();
        Assertions.assertThat(runner.runPart1()).isEqualTo(1833);
    }

    @Test
    public void testRunPart2() {
        var runner = new Day6Runner();
        Assertions.assertThat(runner.runPart2()).isEqualTo(3425);
    }
}