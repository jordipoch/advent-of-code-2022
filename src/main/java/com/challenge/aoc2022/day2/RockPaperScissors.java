package com.challenge.aoc2022.day2;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum RockPaperScissors {
    ROCK(1, "A", "X") {
        @Override
        public int getScoreAgainst(RockPaperScissors other) {
            return switch (other) {
                case ROCK -> 3;
                case PAPER -> 0;
                case SCISSORS -> 6;
            };
        }
    },
    PAPER(2, "B", "Y") {
        @Override
        protected int getScoreAgainst(RockPaperScissors other) {
            return switch (other) {
                case ROCK -> 6;
                case PAPER -> 3;
                case SCISSORS -> 0;
            };
        }
    },
    SCISSORS(3, "C", "Z") {
        @Override
        protected int getScoreAgainst(RockPaperScissors other) {
            return switch (other) {
                case ROCK -> 0;
                case PAPER -> 6;
                case SCISSORS -> 3;
            };
        }
    };

    private final int score;
    private final String codePlayedByOpponent;
    private final String codePlayedByMe;
    RockPaperScissors(int score, String codePlayedByOpponent, String codePlayedByMe) {
        this.score = score;
        this.codePlayedByOpponent = codePlayedByOpponent;
        this.codePlayedByMe = codePlayedByMe;
    }

    private static final Map<String, RockPaperScissors> mapByCodePlayedByOpponent = Arrays.stream(RockPaperScissors.values())
            .collect(Collectors.toMap(RockPaperScissors::getCodePlayedByOpponent, Function.identity()));

    private static final Map<String, RockPaperScissors> mapByCodePlayedByMe = Arrays.stream(RockPaperScissors.values())
            .collect(Collectors.toMap(RockPaperScissors::getCodePlayedByMe, Function.identity()));

    public String getCodePlayedByOpponent() {
        return codePlayedByOpponent;
    }

    public String getCodePlayedByMe() {
        return codePlayedByMe;
    }

    public int getScore() {
        return score;
    }

    public static RockPaperScissors fromCodePlayedByOpponent(String code) {
        return mapByCodePlayedByOpponent.get(code);
    }

    public static RockPaperScissors fromCodePlayedByMe(String code) {
        return mapByCodePlayedByMe.get(code);
    }

    public int getPlayResult(RockPaperScissors other) {
        return score + getScoreAgainst(other);
    }

    protected abstract int getScoreAgainst(RockPaperScissors other);
}
