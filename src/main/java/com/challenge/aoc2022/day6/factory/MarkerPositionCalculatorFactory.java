package com.challenge.aoc2022.day6.factory;

import com.challenge.aoc2022.day6.MarkerPositionCalculator;

public interface MarkerPositionCalculatorFactory {
    MarkerPositionCalculator createMarkerPositionCalculator(int markerSize);
}
