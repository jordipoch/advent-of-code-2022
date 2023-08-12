package com.challenge.aoc2022.day5.main;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RunnerTest {

    @Test
    public void testRunPart1() {
        Runner runner = new Runner();
        assertThat(runner.runPart1()).isEqualTo("QMBMJDFTD");
    }

    @Test
    public void testRunPart2() {
        Runner runner = new Runner();
        assertThat(runner.runPart2()).isEqualTo("NBTVTJNFJ");
    }
}