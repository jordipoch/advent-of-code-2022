package com.challenge.aoc2022.day7.main;

import com.challenge.aoc2022.day7.Day7Controller;
import com.challenge.aoc2022.day7.FolderToRemoveNotFoundException;
import com.challenge.aoc2022.day7.exception.FolderSizesCalculationException;
import com.challenge.aoc2022.day7.main.config.InjectorModule;
import com.challenge.aoc2022.exception.ExecutionException;
import com.google.inject.Guice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day7Runner {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws ExecutionException {
        var runner = new Day7Runner();

        runner.runPart1();
        runner.runPart2();
    }

    public int runPart1() throws ExecutionException {
        var controller = createController();
        final int maxSize = 100_000;

        try {
            var sumOfFolderSizes = controller.calculateSumOfDirectorySizesLessThan(maxSize);
            logger.info("The sum of all directories with folder size of at most {} is: {}", maxSize, sumOfFolderSizes);

            return sumOfFolderSizes;
        } catch (FolderSizesCalculationException e) {
            throw new ExecutionException("Error calculating the sum of directory sizes", e);
        }
    }

    public int runPart2() throws ExecutionException {
        var controller = createController();
        final int fileSystemSize = 70_000_000;
        final int minSpaceNeeded = 30_000_000;

        try {
            var sizeOfDirectoryToRemove = controller.findSizeOfDirectoryToRemove(fileSystemSize, minSpaceNeeded);
            logger.info("The size of the folder that would allow to free up {} if deleted is: {}", minSpaceNeeded, sizeOfDirectoryToRemove);

            return sizeOfDirectoryToRemove;
        } catch (FolderSizesCalculationException | FolderToRemoveNotFoundException e) {
            throw new ExecutionException("Error finding the size of the directory to remove", e);
        }
    }

    private Day7Controller createController() {
        var injector = Guice.createInjector(new InjectorModule());
        return injector.getInstance(Day7Controller.class);
    }
}
