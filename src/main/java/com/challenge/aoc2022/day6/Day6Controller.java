package com.challenge.aoc2022.day6;

import com.challenge.aoc2022.day6.factory.MarkerPositionCalculatorFactory;

import javax.inject.Inject;
import java.util.function.Supplier;

public class Day6Controller {
    private final Supplier<String> packetDataSupplier;
    private final MarkerPositionCalculatorFactory calculatorFactory;

    @Inject
    public Day6Controller(Supplier<String> packetDataSupplier, MarkerPositionCalculatorFactory calculatorFactory) {
        this.packetDataSupplier = packetDataSupplier;
        this.calculatorFactory = calculatorFactory;
    }

    public int calculateMarkerPosition(int markerSize) {
        var calculator = calculatorFactory.createMarkerPositionCalculator(markerSize);
        return calculator.calculateMarkerPosition(packetDataSupplier.get());
    }
}
