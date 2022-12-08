package com.challenge.aoc2022.day1;

import com.challenge.aoc2022.exception.ExecutionException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Day1Test {
    @Test
    public void testPart1() throws ExecutionException {
        Assertions.assertThat(new Day1().runPart1()).isEqualTo(71502);
    }
}