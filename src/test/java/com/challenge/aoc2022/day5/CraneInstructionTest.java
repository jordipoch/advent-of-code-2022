package com.challenge.aoc2022.day5;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CraneInstructionTest {
    @Test
    public void testCreateFromInstruction() {
        var craneInstruction = CraneInstruction.of("move 2 from 3 to 1");

        assertThat(craneInstruction.numCrates()).isEqualTo(2);
        assertThat(craneInstruction.origin()).isEqualTo(3);
        assertThat(craneInstruction.destination()).isEqualTo(1);
    }

    @Test
    public void testCreateFromInstructionWhenWrongFormat() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> CraneInstruction.of("move 2d frome 3 to 1"))
                .withMessage("Wrong format for instruction: move 2d frome 3 to 1");
    }
}