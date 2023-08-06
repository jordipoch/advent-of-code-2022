package com.challenge.aoc2022.day5.crane;

import com.challenge.aoc2022.day5.CraneInstruction;
import com.challenge.aoc2022.day5.ShipSupplies;

class CrateMover9000 extends CrateMover {
    private CrateMover9000(ShipSupplies shipSupplies) {
        super(shipSupplies);
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
}
