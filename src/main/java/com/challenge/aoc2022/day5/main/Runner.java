package com.challenge.aoc2022.day5.main;

import com.challenge.aoc2022.day5.Day5Controller;
import com.challenge.aoc2022.day5.main.config.InjectorModule;
import com.google.inject.Guice;
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

    private Day5Controller createController() {
        var injector = Guice.createInjector(new InjectorModule());
        return injector.getInstance(Day5Controller.class);
    }
}
