package com.challenge.aoc2022.day5.crane;

import com.challenge.aoc2022.day5.CraneInstruction;
import com.challenge.aoc2022.day5.ShipSupplies;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrateMover9000Test {

    private GiantCargoCrane cargoCrane;

    @BeforeMethod
    public void setUp() {
        createCrane();
    }

    @Test
    public void testProcessInstructions() {
        checkCratesAtTheTopAfterInstruction("move 1 from 2 to 1", "DCP");
        checkCratesAtTheTopAfterInstruction("move 3 from 1 to 3", "CZ");
        checkCratesAtTheTopAfterInstruction("move 2 from 2 to 1", "MZ");
        checkCratesAtTheTopAfterInstruction("move 1 from 1 to 2", "CMZ");
    }

    private void createCrane() {
        var shipSupplies = ShipSupplies.builder()
                .addStackOfCrates("NZ")
                .addStackOfCrates("DCM")
                .addStackOfCrates("P")
                .build();
        cargoCrane = GiantCargoCraneFactory.createCrateMover9000(shipSupplies);
    }

    private void checkCratesAtTheTopAfterInstruction(String instruction, String crates) {
        cargoCrane.processInstruction(CraneInstruction.of(instruction));
        Assertions.assertThat(cargoCrane.getTopMostCrates()).isEqualTo(crates);
    }
}