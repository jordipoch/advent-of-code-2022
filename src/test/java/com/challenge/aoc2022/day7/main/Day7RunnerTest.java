package com.challenge.aoc2022.day7.main;

import com.challenge.aoc2022.exception.ExecutionException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class Day7RunnerTest {
    @Test
    public void testRunPart1() throws ExecutionException {
        var runner = new Day7Runner();
        Assertions.assertThat(runner.runPart1()).isEqualTo(1_086_293);
    }

    @Test
    public void testRunPart2() throws ExecutionException {
        var runner = new Day7Runner();
        Assertions.assertThat(runner.runPart2()).isEqualTo(366_028);
    }
}