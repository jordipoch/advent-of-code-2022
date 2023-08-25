package com.challenge.aoc2022.day6;

import com.challenge.aoc2022.day6.factory.MarkerPositionCalculatorFactory;
import com.challenge.aoc2022.day6.factory.MarkerPositionCalculatorUsingArraysFactory;
import com.challenge.aoc2022.day6.factory.MarkerPositionCalculatorUsingStreamsFactory;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6ControllerTest {

    @Test (dataProvider = "calculate marker position test data")
    public void testCalculateMarkerPositionUsingStreams(String message, int markerSize, int expectedPosition) {
        var controller = new Day6Controller(() -> message, new MarkerPositionCalculatorUsingStreamsFactory());

        assertThat(controller.calculateMarkerPosition(markerSize)).isEqualTo(expectedPosition);
    }

    @Test (dataProvider = "calculate marker position test data")
    public void testCalculateMarkerPositionUsingArrays(String message, int markerSize, int expectedPosition) {
        var controller = new Day6Controller(() -> message, new MarkerPositionCalculatorUsingArraysFactory());

        assertThat(controller.calculateMarkerPosition(markerSize)).isEqualTo(expectedPosition);
    }

    @DataProvider(name = "calculate marker position test data")
    protected Iterator<Object[]> getTestsData() {
        return Arrays.asList(new Object[][] {
                {"bvwbjplbgvbhsrlpgdmjqwftvncz", 4, 5},
                {"nppdvjthqldpwncqszvftbrmjlhg", 4, 6},
                {"nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4, 10},
                {"zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4, 11},
                {"mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14, 19},
                {"bvwbjplbgvbhsrlpgdmjqwftvncz", 14, 23},
                {"nppdvjthqldpwncqszvftbrmjlhg", 14, 23},
                {"nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14, 29},
                {"zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14, 26}
        }).iterator();
    }
}