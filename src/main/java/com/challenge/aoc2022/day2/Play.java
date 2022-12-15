package com.challenge.aoc2022.day2;

import static com.challenge.aoc2022.day2.PlayerMove.fromCodePlayedByMe;
import static com.challenge.aoc2022.day2.PlayerMove.fromCodePlayedByOpponent;

public class Play {
    private final PlayerMove opponent;
    private final PlayerMove me;

    private Play(PlayerMove opponent, PlayerMove me) {
        this.opponent = opponent;
        this.me = me;
    }

    public static Play of(PlayerMove opponent, PlayerMove me) {
        return new Play(opponent, me);
    }

    public static Play of(String opponent, String me) {
        return new Play(fromCodePlayedByOpponent(opponent), fromCodePlayedByMe(me));
    }

    public static Play ofStrategy(PlayStrategy strategy) {
        return of(strategy.getOpponent(), strategy.getOpponent().getMyMoveToAchieveResult(strategy.getWantedResult()));
    }

    public int getScore() {
        return me.getPlayResult(opponent);
    }

    @Override
    public String toString() {
        return "(" + opponent + ", " + me + ")";
    }
}
