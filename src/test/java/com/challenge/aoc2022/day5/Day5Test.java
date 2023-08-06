package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.exception.ExecutionException;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5Test {

    @Test
    public void testRunPart1() throws ExecutionException {
        Day5 day5 = new Day5();
        assertThat(day5.runPart1()).isEqualTo("QMBMJDFTD");
    }
}