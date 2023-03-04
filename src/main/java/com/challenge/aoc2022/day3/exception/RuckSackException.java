package com.challenge.aoc2022.day3.exception;

import com.challenge.aoc2022.day3.RuckSack;

public class RuckSackException extends RuntimeException {
    public RuckSackException(RuckSack ruckSack) {
        super("Cannot find a repeated item in rucksack: " + ruckSack);
    }
}
