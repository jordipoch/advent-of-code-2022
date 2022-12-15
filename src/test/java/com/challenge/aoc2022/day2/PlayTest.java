package com.challenge.aoc2022.day2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

import static com.challenge.aoc2022.day2.PlayResult.*;
import static com.challenge.aoc2022.day2.PlayerMove.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayTest {
    @Test(dataProvider = "playCombinations")
    public void testGetScore(PlayerMove opponent, PlayerMove me, int expectedScore) {
        var play = Play.of(opponent, me);
        assertThat(play.getScore()).isEqualTo(expectedScore);
    }

    @Test(dataProvider = "playStrategies")
    public void testCreateFromStrategy(PlayerMove opponent, PlayResult wantedResult, PlayerMove myExpectedMove) {
        var strategy = PlayStrategy.of(opponent, wantedResult);
        assertThat(Play.ofStrategy(strategy)).hasToString(String.format("(%s, %s)", opponent, myExpectedMove));
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

    @DataProvider(name= "playStrategies")
    private Iterator<Object[]> getPlayStrategies() {
        return Arrays.asList(new Object[][] {
                {ROCK, LOSE, SCISSORS},
                {ROCK, DRAW, ROCK},
                {ROCK, WIN, PAPER},
                {PAPER, LOSE, ROCK},
                {PAPER, DRAW, PAPER},
                {PAPER, WIN, SCISSORS},
                {SCISSORS, LOSE, PAPER},
                {SCISSORS, DRAW, SCISSORS},
                {SCISSORS, WIN, ROCK},
        }).listIterator();
    }


}