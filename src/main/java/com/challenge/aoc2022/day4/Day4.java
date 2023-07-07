package com.challenge.aoc2022.day4;

import com.challenge.aoc2022.day4.exception.AssignmentPairsCompartmentException;
import com.challenge.aoc2022.exception.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Day4 {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        var day4 = new Day4();
        try {
            day4.runPart1();
            day4.runPart2();
        } catch (ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public int runPart1() throws ExecutionException {
        try {
            var assignmentPairsContainer = AssignmentPairsContainer.builder()
                    .fromFile("input.txt")
                    .build();
            int numSelfContainedPairs = assignmentPairsContainer.calculateNumberOfSelfContainedAssignmentPairs();
            logger.info("Num of self-contained assignment pairs = {}", numSelfContainedPairs);
            return numSelfContainedPairs;
        } catch (AssignmentPairsCompartmentException e) {
            throw new ExecutionException("Error running day 4 part 1", e);
        }
    }

    public int runPart2() throws ExecutionException {
        try {
            var assignmentPairsContainer = AssignmentPairsContainer.builder()
                    .fromFile("input.txt")
                    .build();
            int numOverlappingPairs = assignmentPairsContainer.calculateNumberOfOverlappingAssignmentPairs();
            logger.info("Num of overlapping assignment pairs = {}", numOverlappingPairs);
            return numOverlappingPairs;
        } catch (AssignmentPairsCompartmentException e) {
            throw new ExecutionException("Error running day 4 part 2", e);
        }
    }
}
