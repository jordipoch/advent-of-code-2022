package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.day5.crane.GiantCargoCraneType;
import com.challenge.aoc2022.day5.exception.GiantCargoCraneControllerException;
import com.challenge.aoc2022.exception.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Day5 {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        var day5 = new Day5();
        try {
            day5.runPart1();
            day5.runPart2();
        } catch (ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public String runPart1() throws ExecutionException {
        try {
            var controller = GiantCargoCraneController.builder()
                    .withSupplies("ship_supplies.txt")
                    .withInstructions("instructions.txt")
                    .build();
            String topMostCrates = controller.process();
            logger.info("Crates at the top of the stacks after processing instructions with CrateMover 9000: {}", topMostCrates);
            return topMostCrates;
        } catch (GiantCargoCraneControllerException e) {
            throw new ExecutionException("Error running day 5 part 1", e);
        }
    }

    public String runPart2() throws ExecutionException {
        try {
            var controller = GiantCargoCraneController.builder()
                    .withCraneType(GiantCargoCraneType.CRATE_MOVER_9001)
                    .withSupplies("ship_supplies.txt")
                    .withInstructions("instructions.txt")
                    .build();
            String topMostCrates = controller.process();
            logger.info("Crates at the top of the stacks after processing instructions with CrateMover 9001: {}", topMostCrates);
            return topMostCrates;
        } catch (GiantCargoCraneControllerException e) {
            throw new ExecutionException("Error running day 5 part 2", e);
        }
    }
}
