package com.challenge.aoc2022.day5;

public class GiantCargoCraneFactory {
    private GiantCargoCraneFactory() {
    }

    public static GiantCargoCrane createCrateMover9000(ShipSupplies shipSupplies) {
        return CrateMover9000.of(shipSupplies);
    }
}