package com.challenge.aoc2022.day5;

public class CrateMover9000 implements GiantCargoCrane {
    private final ShipSupplies shipSupplies;

    private CrateMover9000(ShipSupplies shipSupplies) {
        this.shipSupplies = shipSupplies;
    }

    public static CrateMover9000 of(ShipSupplies shipSupplies) {
        return new CrateMover9000(shipSupplies);
    }

    @Override
    public void processInstruction(CraneInstruction instruction) {
        for (int i = 0; i < instruction.numCrates(); i++) {
            var crate = shipSupplies.popCrateFromStack(instruction.origin());
            shipSupplies.pushCrateToStack(crate, instruction.destination());
        }
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
