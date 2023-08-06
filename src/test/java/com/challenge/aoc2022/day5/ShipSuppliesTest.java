package com.challenge.aoc2022.day5;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class ShipSuppliesTest {
    private ShipSupplies defaultShipSupplies;

    @BeforeMethod
    public void setUp() {
        defaultShipSupplies = ShipSupplies.builder()
                .addStackOfCrates("NZ")
                .addStackOfCrates("DCM")
                .addStackOfCrates("P")
                .build();
    }

    @Test
    public void testToString() {
        var stringRepresentation = defaultShipSupplies.toString();
        System.out.println(stringRepresentation);
        assertThat(stringRepresentation).hasToString("""
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s"""
        );
    }

    @Test
    public void testPopCrateFromStack() {
        var crate = defaultShipSupplies.popCrateFromStack(1);
        assertThat(crate).isEqualTo('N');

        var stringRepresentation = defaultShipSupplies.toString();
        System.out.println(stringRepresentation);
        assertThat(stringRepresentation).hasToString("""
                    [D]   \s
                    [C]   \s
                [Z] [M] [P]
                 1   2   3\s"""
        );
    }

    @Test
    public void testPushCrateToStack() {
        defaultShipSupplies.pushCrateToStack('X', 3);

        var stringRepresentation = defaultShipSupplies.toString();
        System.out.println(stringRepresentation);
        assertThat(stringRepresentation).hasToString("""
                    [D]   \s
                [N] [C] [X]
                [Z] [M] [P]
                 1   2   3\s"""
        );
    }

    @Test
    public void testGetTopCratesFromStacks() {
        var crates = defaultShipSupplies.getTopCratesFromStacks();
        assertThat(crates).isEqualTo("NDP");

        var stringRepresentation = defaultShipSupplies.toString();
        System.out.println(stringRepresentation);
        assertThat(stringRepresentation).hasToString("""
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s"""
        );
    }

    @Test
    public void testGetCrateFromStackWhenWrongIndex() {
        assertThatIllegalArgumentException().isThrownBy(() -> defaultShipSupplies.popCrateFromStack(0))
                .withMessage("Wrong stack number (%d). Expecting a value in range [1, %d]", 0, defaultShipSupplies.getNumOfStacks());

    }

    @Test
    public void testGetCrateFromStackWhenEmptyStack() {
        defaultShipSupplies.popCrateFromStack(3);
        assertThatIllegalStateException().isThrownBy(() -> defaultShipSupplies.popCrateFromStack(3))
                .withMessage("The stack number %d is empty!", 3);

    }
}