package com.challenge.aoc2022.day5.main;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5RunnerTest {

    @Test
    public void testRunPart1() {
        Day5Runner day5Runner = new Day5Runner();
        assertThat(day5Runner.runPart1()).isEqualTo("QMBMJDFTD");
    }

    @Test
    public void testRunPart2() {
        Day5Runner day5Runner = new Day5Runner();
        assertThat(day5Runner.runPart2()).isEqualTo("NBTVTJNFJ");
    }
}