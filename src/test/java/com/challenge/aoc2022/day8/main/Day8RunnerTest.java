package com.challenge.aoc2022.day8.main;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class Day8RunnerTest {

    @Test
    public void testRunPart1() {
        var runner = new Day8Runner();

        assertThat(runner.runPart1()).isEqualTo(1533);
    }
}