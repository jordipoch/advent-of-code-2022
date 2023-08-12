package com.challenge.aoc2022.day5.main;

import com.challenge.aoc2022.day5.Day5Controller;
import com.challenge.aoc2022.input.InputFileDataLoaderForProd;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        var runner = new Runner();
        runner.runPart1();
        runner.runPart2();
    }

    public String runPart1() {
        var controller = createController();
        String topMostCrates = controller.runPart1();
        logger.info("Crates at the top of the stacks after processing instructions with CrateMover 9000: {}", topMostCrates);
        return topMostCrates;
    }

    public String runPart2() {
        var controller = createController();
        String topMostCrates = controller.runPart2();
        logger.info("Crates at the top of the stacks after processing instructions with CrateMover 9001: {}", topMostCrates);
        return topMostCrates;
    }

    private static Day5Controller createController() {
        var shipSuppliesCreator = new ShipSupplierCreator(new InputFileDataLoaderForProd(5, "ship_supplies.txt"));
        var craneInstructionsCreator = new CraneInstructionsCreator(new InputFileDataLoaderForProd(5, "instructions.txt"));
        return Day5Controller.of(shipSuppliesCreator, craneInstructionsCreator);
    }
}
