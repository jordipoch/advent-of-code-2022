package com.challenge.aoc2022.day6.main;

import com.challenge.aoc2022.day6.Day6Controller;
import com.challenge.aoc2022.day6.main.config.InjectorModule;
import com.google.inject.Guice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day6Runner {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        var runner = new Day6Runner();
        runner.runPart1();
    }

    public int runPart1() {
        var controller = createController();

        var markerPosition = controller.calculateMarkerPosition();
        logger.info("The start-of-packet marker ends at position {}", markerPosition);
        return markerPosition;
    }

    private Day6Controller createController() {
        var injector = Guice.createInjector(new InjectorModule());
        return injector.getInstance(Day6Controller.class);
    }
}
