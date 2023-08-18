package com.challenge.aoc2022.day6.factory;

import com.challenge.aoc2022.day6.MarkerPositionCalculator;
import com.challenge.aoc2022.day6.MarkerPositionCalculatorImplUsingStreams;

public class MarkerPositionCalculatorUsingStreamsFactory implements MarkerPositionCalculatorFactory {
    @Override
    public MarkerPositionCalculator createMarkerPositionCalculator(int markerSize) {
        return new MarkerPositionCalculatorImplUsingStreams(markerSize);
    }
}
