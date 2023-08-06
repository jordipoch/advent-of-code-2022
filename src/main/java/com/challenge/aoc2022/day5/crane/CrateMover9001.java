package com.challenge.aoc2022.day5.crane;

import com.challenge.aoc2022.day5.CraneInstruction;
import com.challenge.aoc2022.day5.ShipSupplies;

import java.util.ArrayDeque;

class CrateMover9001 extends CrateMover {
    private CrateMover9001(ShipSupplies shipSupplies) {
        super(shipSupplies);
    }

    public static CrateMover9001 of(ShipSupplies shipSupplies) {
        return new CrateMover9001(shipSupplies);
    }

    @Override
    public void processInstruction(CraneInstruction instruction) {
        var stack = new ArrayDeque<Character>();
        for (int i = 0; i < instruction.numCrates(); i++)
            stack.push(shipSupplies.popCrateFromStack(instruction.origin()));

        while (!stack.isEmpty())
            shipSupplies.pushCrateToStack(stack.pop(), instruction.destination());
    }
}
