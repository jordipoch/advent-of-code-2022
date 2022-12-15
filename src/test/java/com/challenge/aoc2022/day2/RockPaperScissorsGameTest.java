package com.challenge.aoc2022.day2;

import com.challenge.aoc2022.day2.exception.RockPaperScissorsGameException;
import org.testng.annotations.Test;

import static com.challenge.aoc2022.day2.RockPaperScissorsGame.Builder.createFrom;
import static com.challenge.aoc2022.day2.RockPaperScissorsGame.Builder.createFromStrategies;
import static org.assertj.core.api.Assertions.assertThat;

public class RockPaperScissorsGameTest {
    private static final String[] INPUT = {"A Y", "B X", "C Z"};

    @Test
    public void testBuildGameFromPlays() {
        var game = createFrom(INPUT);
        assertThat(game).hasToString("plays=[(ROCK, PAPER), (PAPER, ROCK), (SCISSORS, SCISSORS)]");
    }

    @Test
    public void testBuildGameFromStrategies() {
        var game = createFromStrategies(INPUT);
        assertThat(game).hasToString("plays=[(ROCK, ROCK), (PAPER, ROCK), (SCISSORS, ROCK)]");
    }


    @Test
    public void testCalculateGameScore() {
        var game = createFrom(INPUT);
        assertThat(game.calculateGameScore()).isEqualTo(15);
    }

    @Test
    public void testCalculateGameScoreFollowingInstructions() {
        var game = createFromStrategies(INPUT);
        assertThat(game.calculateGameScore()).isEqualTo(12);
    }

    @Test
    public void testCalculateGameScoreFromFile() throws RockPaperScissorsGameException {
        var game = RockPaperScissorsGame.builder()
                        .isTest()
                        .fromFile("example_input.txt")
                        .build();
        assertThat(game.calculateGameScore()).isEqualTo(15);
    }
}