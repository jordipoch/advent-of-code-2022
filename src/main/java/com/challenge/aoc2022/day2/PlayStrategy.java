package com.challenge.aoc2022.day2;

import static com.challenge.aoc2022.day2.PlayResult.fromCode;
import static com.challenge.aoc2022.day2.PlayerMove.fromCodePlayedByOpponent;

public class PlayStrategy {
    private final PlayerMove opponent;
    private final PlayResult wantedResult;

    private PlayStrategy(PlayerMove opponent, PlayResult wantedResult) {
        this.opponent = opponent;
        this.wantedResult = wantedResult;
    }

    public static PlayStrategy of(PlayerMove opponent, PlayResult wantedResult) {
        return new PlayStrategy(opponent, wantedResult);
    }

    public static PlayStrategy of(String opponent, String wantedResult) {
        return new PlayStrategy(fromCodePlayedByOpponent(opponent), fromCode(wantedResult));
    }

    public PlayerMove getOpponent() {
        return opponent;
    }

    public PlayResult getWantedResult() {
        return wantedResult;
    }

    @Override
    public String toString() {
        return "(" + opponent + ", " + wantedResult + ")";
    }
}
