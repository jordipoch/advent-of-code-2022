package com.challenge.aoc2022.day4;

import com.challenge.aoc2022.day4.exception.AssignmentPairsCompartmentException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
public class AssignmentPairsContainerTest {

    @Test
    public void testCalculateNumberOfSelfContainedAssignmentPairs() throws AssignmentPairsCompartmentException {
        var assignmentPairsContainer = AssignmentPairsContainer.builder()
                .forTest()
                .fromFile("example_input.txt")
                .build();

        Assertions.assertThat(assignmentPairsContainer.calculateNumberOfSelfContainedAssignmentPairs()).isEqualTo(2);
    }

    @Test
    public void testCalculateNumberOverlappedAssignmentPairs() throws AssignmentPairsCompartmentException {
        var assignmentPairsContainer = AssignmentPairsContainer.builder()
                .forTest()
                .fromFile("example_input.txt")
                .build();

        Assertions.assertThat(assignmentPairsContainer.calculateNumberOfOverlappingAssignmentPairs()).isEqualTo(4);
    }
}