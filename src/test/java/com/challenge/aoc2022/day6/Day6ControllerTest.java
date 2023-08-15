package com.challenge.aoc2022.day6;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class Day6ControllerTest {

    @Test
    public void testCalculateMarkerPositionUsingArrays() {
        MarkerPositionCalculator markerPositionCalculator = new MarkerPositionCalculatorImplUsingArrays();
        var controller = new Day6Controller(() -> "nppdvjthqldpwncqszvftbrmjlhg", markerPositionCalculator);

        Assertions.assertThat(controller.calculateMarkerPosition()).isEqualTo(6);
    }

    @Test
    public void testCalculateMarkerPositionUsingStreams() {
        MarkerPositionCalculator markerPositionCalculator = new MarkerPositionCalculatorImplUsingStreams();
        var controller = new Day6Controller(() -> "bvwbjplbgvbhsrlpgdmjqwftvncz", markerPositionCalculator);

        Assertions.assertThat(controller.calculateMarkerPosition()).isEqualTo(5);
    }

}