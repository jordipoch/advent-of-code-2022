package com.challenge.aoc2022.day5;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class ShipsSuppliesTest {
    private ShipsSupplies shipsSupplies;

    @BeforeMethod
    public void setUp() {
        shipsSupplies = ShipsSupplies.builder()
                .withStackOfCrates("NZ")
                .withStackOfCrates("DCM")
                .withStackOfCrates("P")
                .build();
    }

    @Test
    public void testShipsSuppliesCreation() {
        var stringRepresentation = shipsSupplies.toString();
        System.out.println(stringRepresentation);
        assertThat(stringRepresentation).hasToString("""
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s"""
        );
    }

    @Test
    public void testGetCrateFromStack() {
        var crate = shipsSupplies.getCrateFromStack(1);
        assertThat(crate).isEqualTo('N');

        var stringRepresentation = shipsSupplies.toString();
        System.out.println(stringRepresentation);
        assertThat(stringRepresentation).hasToString("""
                    [D]   \s
                    [C]   \s
                [Z] [M] [P]
                 1   2   3\s"""
        );
    }

    @Test
    public void testPutCrateOnTopOfStack() {
        shipsSupplies.putCrateOnTopOfStack('X', 3);

        var stringRepresentation = shipsSupplies.toString();
        System.out.println(stringRepresentation);
        assertThat(stringRepresentation).hasToString("""
                    [D]   \s
                [N] [C] [X]
                [Z] [M] [P]
                 1   2   3\s"""
        );
    }

    @Test
    public void testGetCrateFromStackWhenWrongIndex() {
        assertThatIllegalArgumentException().isThrownBy(() -> shipsSupplies.getCrateFromStack(0))
                .withMessage("Wrong stack number (%d). Expecting a value in range [1, %d]", 0, shipsSupplies.getNumOfStacks());

    }

    @Test
    public void testGetCrateFromStackWhenEmptyStack() {
        shipsSupplies.getCrateFromStack(3);
        assertThatIllegalStateException().isThrownBy(() -> shipsSupplies.getCrateFromStack(3))
                .withMessage("The stack number %d is empty!", 3);

    }
}