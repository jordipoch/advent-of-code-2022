package com.challenge.aoc2022.day5;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StackOfCratesTest {
    @Test
    public void testStackOfCratesCreation() {
        assertThat(StackOfCrates.of("ABCDE")).hasToString("[A, B, C, D, E]");
    }

    @Test
    public void testGetReverseList() {
        assertThat(StackOfCrates.of("ABCDE").getCrateListInReversOrder()).containsExactly('E', 'D', 'C', 'B', 'A');
    }

}