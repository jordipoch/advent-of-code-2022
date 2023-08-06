package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.day5.crane.GiantCargoCraneType;
import com.challenge.aoc2022.day5.exception.GiantCargoCraneControllerException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.challenge.aoc2022.day5.crane.GiantCargoCraneType.CRATE_MOVER_9000;
import static com.challenge.aoc2022.day5.crane.GiantCargoCraneType.CRATE_MOVER_9001;

public class GiantCargoCraneControllerTest {

    @Test
    public void testProcessWithCrateMover9000() throws GiantCargoCraneControllerException {
        var craneController = createCraneController(CRATE_MOVER_9000);
        Assertions.assertThat(craneController.process()).isEqualTo("CMZ");
    }

    @Test
    public void testProcessWithCrateMover9001() throws GiantCargoCraneControllerException {
        var craneController = createCraneController(CRATE_MOVER_9001);
        Assertions.assertThat(craneController.process()).isEqualTo("MCD");
    }

    private GiantCargoCraneController createCraneController(GiantCargoCraneType craneType) throws GiantCargoCraneControllerException {
        return GiantCargoCraneController.builder()
                .withCraneType(craneType)
                .forTest()
                .withSupplies("ship_supplies.txt")
                .withInstructions("instructions.txt")
                .build();
    }
}