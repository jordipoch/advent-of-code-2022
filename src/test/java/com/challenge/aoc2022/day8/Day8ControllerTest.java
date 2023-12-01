package com.challenge.aoc2022.day8;

import com.challenge.aoc2022.input.InputFileDataLoaderForTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day8ControllerTest {
    @Test
    public void testCalculateSumOfVisibleTrees() {
        var controller = new Day8Controller(new TreeHeightsSupplier(
                new InputFileDataLoaderForTest(8, "example_input.txt")
        ));

        assertThat(controller.calculateSumOfVisibleTrees()).isEqualTo(21);
    }
}