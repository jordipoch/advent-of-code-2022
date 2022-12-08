package com.challenge.aoc2022.day2;

import com.challenge.aoc2022.exception.ExecutionException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class Day2Test {

    @Test
    public void testRunPart1() throws ExecutionException {
        Assertions.assertThat(new Day2().runPart1()).isEqualTo(11_475);
    }
}