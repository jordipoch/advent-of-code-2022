package com.challenge.aoc2022.day2;

import com.challenge.aoc2022.day2.exception.RockPaperScissorsGameException;
import org.testng.annotations.Test;

import static com.challenge.aoc2022.day2.RockPaperScissorsGame.Builder.createFrom;
import static org.assertj.core.api.Assertions.assertThat;

public class RockPaperScissorsGameTest {
    private static final String[] PLAYS = {"A Y", "B X", "C Z"};

    @Test
    public void testBuildGame() {
        var game = createFrom(PLAYS);
        assertThat(game).hasToString("plays=[(ROCK, PAPER), (PAPER, ROCK), (SCISSORS, SCISSORS)]");
    }

    @Test
    public void testCalculateGameScore() {
        var game = createFrom(PLAYS);
        assertThat(game.calculateGameScore()).isEqualTo(15);
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