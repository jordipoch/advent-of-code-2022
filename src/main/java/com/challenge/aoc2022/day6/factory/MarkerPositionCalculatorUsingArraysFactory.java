package com.challenge.aoc2022.day6.factory;

import com.challenge.aoc2022.day6.MarkerPositionCalculator;
import com.challenge.aoc2022.day6.MarkerPositionCalculatorImplUsingArrays;

public class MarkerPositionCalculatorUsingArraysFactory implements MarkerPositionCalculatorFactory {
    @Override
    public MarkerPositionCalculator createMarkerPositionCalculator(int markerSize) {
        return new MarkerPositionCalculatorImplUsingArrays(markerSize);
    }
}
