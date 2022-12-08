package com.challenge.aoc2022.day1;

import com.challenge.aoc2022.day1.exception.FoodCaloriesCounterException;
import com.challenge.aoc2022.exception.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day1 {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        var day1 = new Day1();
        try {
            day1.runPart1();
        } catch(ExecutionException e) {
            logger.error(e.getMessage());
        }
    }

    int runPart1() throws ExecutionException {
        try {
            var foodCaloriesCounter = FoodCaloriesCounter.builder()
                    .withFilename("input.txt")
                    .build();
            var maxCalories = foodCaloriesCounter.calculateMaxCaloriesCarriedByAnElf();
            logger.info("Max calories = {}", maxCalories);
            return maxCalories;
        } catch (FoodCaloriesCounterException e) {
            throw new ExecutionException("Error running day 1 part 1", e);
        }
    }
}
