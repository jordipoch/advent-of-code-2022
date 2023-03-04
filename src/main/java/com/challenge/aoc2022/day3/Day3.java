package com.challenge.aoc2022.day3;

import com.challenge.aoc2022.day3.exception.FoodSuppliesException;
import com.challenge.aoc2022.exception.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.challenge.aoc2022.day3.FoodSupplies.Builder.aFoodSupplies;

public class Day3 {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        var day3 = new Day3();
        try {
            day3.runPart1();
        } catch (ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public int runPart1() throws ExecutionException {
        try {
            var foodSupplies = aFoodSupplies()
                    .items("input.txt")
                    .build();
            int sum = foodSupplies.calculateSumOfPriorities();
            logger.info("Sum of priorities of all repeated items in the rucksacks = {}", sum);
            return sum;
        } catch (FoodSuppliesException e) {
            throw new ExecutionException("Error running day 3 part 1", e);
        }
    }
}
