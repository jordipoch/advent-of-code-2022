package com.challenge.aoc2022.day6;

import javax.inject.Inject;
import java.util.function.Supplier;

public class Day6Controller {
    private final Supplier<String> PacketDataSupplier;
    private final MarkerPositionCalculator markerPositionCalculator;

    @Inject
    public Day6Controller(Supplier<String> PacketDataSupplier, MarkerPositionCalculator markerPositionCalculator) {
        this.PacketDataSupplier = PacketDataSupplier;
        this.markerPositionCalculator = markerPositionCalculator;
    }

    public int calculateMarkerPosition() {
        return markerPositionCalculator.calculateMarkerPosition(PacketDataSupplier.get());
    }
}
