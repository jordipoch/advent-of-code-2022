package com.challenge.aoc2022.day5;

public class GiantCargoCrane {
    private final ShipSupplies shipSupplies;

    private GiantCargoCrane(ShipSupplies shipSupplies) {
        this.shipSupplies = shipSupplies;
    }

    public static GiantCargoCrane of(ShipSupplies shipSupplies) {
        return new GiantCargoCrane(shipSupplies);
    }

    public void processInstruction(CraneInstruction instruction) {
        for (int i = 0; i < instruction.numCrates(); i++) {
            var crate = shipSupplies.popCrateFromStack(instruction.origin());
            shipSupplies.pushCrateToStack(crate, instruction.destination());
        }
    }

    public String getTopMostCrates() {
        return shipSupplies.getTopCratesFromStacks();
    }

    @Override
    public String toString() {
        return shipSupplies.toString();
    }
}
