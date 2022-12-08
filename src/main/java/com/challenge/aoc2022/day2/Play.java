package com.challenge.aoc2022.day2;

import static com.challenge.aoc2022.day2.RockPaperScissors.fromCodePlayedByMe;
import static com.challenge.aoc2022.day2.RockPaperScissors.fromCodePlayedByOpponent;

public class Play {
    private final RockPaperScissors opponent;
    private final RockPaperScissors me;

    private Play(RockPaperScissors opponent, RockPaperScissors me) {
        this.opponent = opponent;
        this.me = me;
    }

    public static Play of(RockPaperScissors opponent, RockPaperScissors me) {
        return new Play(opponent, me);
    }

    public static Play of(String opponent, String me) {
        return new Play(fromCodePlayedByOpponent(opponent), fromCodePlayedByMe(me));
    }

    public int getScore() {
        return me.getPlayResult(opponent);
    }

    @Override
    public String toString() {
        return "(" + opponent + ", " + me + ")";
    }
}
