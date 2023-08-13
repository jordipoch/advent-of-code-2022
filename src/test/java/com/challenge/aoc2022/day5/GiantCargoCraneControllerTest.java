package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.day5.main.CraneInstructionsSupplier;
import com.challenge.aoc2022.day5.main.ShipSuppliesSupplier;
import com.challenge.aoc2022.input.InputFileDataLoaderForTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.challenge.aoc2022.day5.crane.GiantCargoCraneFactory.createCrateMover9000;
import static com.challenge.aoc2022.day5.crane.GiantCargoCraneFactory.createCrateMover9001;

public class GiantCargoCraneControllerTest {
    private ShipSuppliesSupplier shipSuppliesSupplier;
    private CraneInstructionsSupplier craneInstructionsSupplier;

    @BeforeMethod
    public void setUp() {
        shipSuppliesSupplier = new ShipSuppliesSupplier(new InputFileDataLoaderForTest(5, "ship_supplies.txt"));
        craneInstructionsSupplier = new CraneInstructionsSupplier(new InputFileDataLoaderForTest(5, "instructions.txt"));
    }

    @Test
    public void testProcessWithCrateMover9000() {
        var craneController = GiantCargoCraneController.of(
                createCrateMover9000(shipSuppliesSupplier.get()),
                craneInstructionsSupplier.get()
        );
        Assertions.assertThat(craneController.process()).isEqualTo("CMZ");
    }

    @Test
    public void testProcessWithCrateMover9001() {
        var craneController = GiantCargoCraneController.of(
                createCrateMover9001(shipSuppliesSupplier.get()),
                craneInstructionsSupplier.get()
        );
        Assertions.assertThat(craneController.process()).isEqualTo("MCD");
    }
}