package com.challenge.aoc2022.day8.main;

import com.challenge.aoc2022.day8.main.config.InjectorModule;
import com.challenge.aoc2022.day8.Day8Controller;
import com.challenge.aoc2022.exception.ExecutionException;
import com.google.inject.Guice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day8Runner {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws ExecutionException {
        var runner = new Day8Runner();

        runner.runPart1();
    }

    public int runPart1() {
        var controller = createController();

        var numVisibleTrees = controller.calculateSumOfVisibleTrees();
        logger.info("The sum of visible trees is {}", numVisibleTrees);

        return numVisibleTrees;
    }

    private Day8Controller createController() {
        var injector = Guice.createInjector(new InjectorModule());
        return injector.getInstance(Day8Controller.class);
    }
}
