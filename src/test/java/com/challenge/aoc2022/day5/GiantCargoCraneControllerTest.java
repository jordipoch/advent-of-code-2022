package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.day5.exception.GiantCargoCraneControllerException;
import com.challenge.aoc2022.day5.exception.ShipSuppliesCreationException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

public class GiantCargoCraneControllerTest {

    @Test
    public void testProcess() throws GiantCargoCraneControllerException {
        var craneController = GiantCargoCraneController.builder()
                .forTest()
                .withSupplies("ship_supplies.txt")
                .withInstructions("instructions.txt")
                .build();

        Assertions.assertThat(craneController.process()).isEqualTo("CMZ");
    }
}