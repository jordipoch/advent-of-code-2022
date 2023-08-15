package com.challenge.aoc2022.day6;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkerPositionCalculatorImplUsingArraysTest extends MarkerPositionCalculatorTest {
    @BeforeClass
    public void setUp() {
        markerPositionCalculator = new MarkerPositionCalculatorImplUsingArrays();
    }

    @Test (dataProvider = "simple tests")
    public void getMarkerPositionBasicTests(String data, int expectedMarkerPosition) {
        assertThat(markerPositionCalculator.calculateMarkerPosition(data)).isEqualTo(expectedMarkerPosition);
    }

    @Test (dataProvider = "complex tests")
    public void getMarkerPositionComplexTests(String data, int expectedMarkerPosition) {
        assertThat(markerPositionCalculator.calculateMarkerPosition(data)).isEqualTo(expectedMarkerPosition);
    }
}