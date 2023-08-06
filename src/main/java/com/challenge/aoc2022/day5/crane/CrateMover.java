package com.challenge.aoc2022.day5.crane;

import com.challenge.aoc2022.day5.ShipSupplies;

abstract class CrateMover implements GiantCargoCrane {
    protected final ShipSupplies shipSupplies;

    protected CrateMover(ShipSupplies shipSupplies) {
        this.shipSupplies = shipSupplies;
    }

    @Override
    public String getTopMostCrates() {
        return shipSupplies.getTopCratesFromStacks();
    }

    @Override
    public String toString() {
        return shipSupplies.toString();
    }
}
