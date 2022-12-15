package com.challenge.aoc2022.day2;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PlayerMove {
    ROCK(1, "A", "X") {
        @Override
        public int getScoreAgainst(PlayerMove other) {
            return switch (other) {
                case ROCK -> 3;
                case PAPER -> 0;
                case SCISSORS -> 6;
            };
        }

        @Override
        protected PlayerMove getMyMoveToAchieveResult(PlayResult result) {
            return switch (result) {
                case LOSE -> SCISSORS;
                case DRAW -> ROCK;
                case WIN -> PAPER;
            };
        }
    },
    PAPER(2, "B", "Y") {
        @Override
        protected int getScoreAgainst(PlayerMove other) {
            return switch (other) {
                case ROCK -> 6;
                case PAPER -> 3;
                case SCISSORS -> 0;
            };
        }

        @Override
        protected PlayerMove getMyMoveToAchieveResult(PlayResult result) {
            return switch (result) {
                case LOSE -> ROCK;
                case DRAW -> PAPER;
                case WIN -> SCISSORS;
            };
        }
    },
    SCISSORS(3, "C", "Z") {
        @Override
        protected int getScoreAgainst(PlayerMove other) {
            return switch (other) {
                case ROCK -> 0;
                case PAPER -> 6;
                case SCISSORS -> 3;
            };
        }

        @Override
        protected PlayerMove getMyMoveToAchieveResult(PlayResult result) {
            return switch (result) {
                case LOSE -> PAPER;
                case DRAW -> SCISSORS;
                case WIN -> ROCK;
            };
        }
    };

    private final int score;
    private final String codePlayedByOpponent;
    private final String codePlayedByMe;
    PlayerMove(int score, String codePlayedByOpponent, String codePlayedByMe) {
        this.score = score;
        this.codePlayedByOpponent = codePlayedByOpponent;
        this.codePlayedByMe = codePlayedByMe;
    }

    private static final Map<String, PlayerMove> mapByCodePlayedByOpponent = Arrays.stream(PlayerMove.values())
            .collect(Collectors.toMap(PlayerMove::getCodePlayedByOpponent, Function.identity()));

    private static final Map<String, PlayerMove> mapByCodePlayedByMe = Arrays.stream(PlayerMove.values())
            .collect(Collectors.toMap(PlayerMove::getCodePlayedByMe, Function.identity()));

    public String getCodePlayedByOpponent() {
        return codePlayedByOpponent;
    }

    public String getCodePlayedByMe() {
        return codePlayedByMe;
    }

    public int getScore() {
        return score;
    }

    public static PlayerMove fromCodePlayedByOpponent(String code) {
        return mapByCodePlayedByOpponent.get(code);
    }

    public static PlayerMove fromCodePlayedByMe(String code) {
        return mapByCodePlayedByMe.get(code);
    }

    public int getPlayResult(PlayerMove other) {
        return score + getScoreAgainst(other);
    }

    protected abstract int getScoreAgainst(PlayerMove other);

    protected abstract PlayerMove getMyMoveToAchieveResult(PlayResult result);
}
