package com.challenge.aoc2022.day2;

import com.challenge.aoc2022.day2.exception.RockPaperScissorsGameException;
import com.challenge.aoc2022.exception.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day2 {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        var day2 = new Day2();
        try {
            day2.runPart1();
        } catch (ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public int runPart1() throws ExecutionException {
        try {
            var game = RockPaperScissorsGame.builder()
                    .fromFile("input.txt")
                    .build();
            var score = game.calculateGameScore();
            logger.info("Rock/Paper/Scissors tournament score = {}", score);
            return score;
        } catch (RockPaperScissorsGameException e) {
            throw new ExecutionException("Error running day 2 part 1", e);
        }
    }
}
