package com.challenge.aoc2022.day6;

public abstract class AbstractMarkerPositionCalculator implements MarkerPositionCalculator {
    protected final int markerSize;

    protected AbstractMarkerPositionCalculator(int markerSize) {
        this.markerSize = markerSize;
    }
}
