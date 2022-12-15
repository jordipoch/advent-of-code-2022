package com.challenge.aoc2022.day2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

import static com.challenge.aoc2022.day2.PlayResult.*;
import static com.challenge.aoc2022.day2.PlayerMove.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerMoveTest {
    @Test(dataProvider = "movesAndResults")
    public void testGetMyMoveToAchieveResult(PlayerMove opponentMove, PlayResult result, PlayerMove myExpectedMove) {
        assertThat(opponentMove.getMyMoveToAchieveResult(result)).isEqualTo(myExpectedMove);
    }

    @DataProvider(name="movesAndResults")
    private Iterator<Object[]> getMovesAndResults() {
        return Arrays.asList(new Object[][] {
                {ROCK, LOSE, SCISSORS},
                {ROCK, DRAW, ROCK},
                {ROCK, WIN, PAPER},
                {PAPER, LOSE, ROCK},
                {PAPER, DRAW, PAPER},
                {PAPER, WIN, SCISSORS},
                {SCISSORS, LOSE, PAPER},
                {SCISSORS, DRAW, SCISSORS},
                {SCISSORS, WIN, ROCK}
        }).listIterator();
    }
}