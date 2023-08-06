package com.challenge.aoc2022.day5.crane;

import com.challenge.aoc2022.day5.ShipSupplies;

class GiantCargoCraneFactory {
    private GiantCargoCraneFactory() {
    }

    public static GiantCargoCrane createCrateMover9000(ShipSupplies shipSupplies) {
        return CrateMover9000.of(shipSupplies);
    }

    public static GiantCargoCrane createCrateMover9001(ShipSupplies shipSupplies) {
        return CrateMover9001.of(shipSupplies);
    }
}