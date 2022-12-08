package com.challenge.aoc2022.day2;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

import static com.challenge.aoc2022.day2.RockPaperScissors.*;

public class PlayTest {
    @Test(dataProvider = "playCombinations")
    public void testGetScore(RockPaperScissors opponent, RockPaperScissors me, int expectedScore) {
        var play = Play.of(opponent, me);
        Assertions.assertThat(play.getScore()).isEqualTo(expectedScore);
    }

    @DataProvider(name = "playCombinations")
    private Iterator<Object[]> getPlayCombinations() {
        return Arrays.asList(new Object[][] {
                {ROCK, ROCK, 4},
                {ROCK, PAPER, 8},
                {ROCK, SCISSORS, 3},
                {PAPER, ROCK, 1},
                {PAPER, PAPER, 5},
                {PAPER, SCISSORS, 9},
                {SCISSORS, ROCK, 7},
                {SCISSORS, PAPER, 2},
                {SCISSORS, SCISSORS, 6}
        }).listIterator();
    }
}