package com.challenge.aoc2022.day5;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Supplier;

import static com.challenge.aoc2022.day5.crane.GiantCargoCraneFactory.createCrateMover9000;
import static com.challenge.aoc2022.day5.crane.GiantCargoCraneFactory.createCrateMover9001;

public class Day5Controller {
    private final Supplier<ShipSupplies> shipSuppliesSupplier;
    private final Supplier<List<CraneInstruction>> craneInstructionsSupplier;

    @Inject
    public Day5Controller(Supplier<ShipSupplies> shipSuppliesSupplier, Supplier<List<CraneInstruction>> craneInstructionsSupplier) {
        this.shipSuppliesSupplier = shipSuppliesSupplier;
        this.craneInstructionsSupplier = craneInstructionsSupplier;
    }

    public static Day5Controller of(Supplier<ShipSupplies> shipSuppliesSupplier, Supplier<List<CraneInstruction>> craneInstructionsSupplier) {
        return new Day5Controller(shipSuppliesSupplier, craneInstructionsSupplier);
    }

    public String runPart1() {
        var craneController = GiantCargoCraneController.of(
                createCrateMover9000(shipSuppliesSupplier.get()), craneInstructionsSupplier.get());
        return craneController.process();
    }

    public String runPart2() {
        var craneController = GiantCargoCraneController.of(
                createCrateMover9001(shipSuppliesSupplier.get()), craneInstructionsSupplier.get());
        return craneController.process();
    }
}
