package com.challenge.aoc2022.day5;

import java.util.regex.Pattern;

public record CraneInstruction(int numCrates, int origin, int destination) {

    private static final Pattern PATTERN = Pattern.compile("^move (\\d{1,2}) from (\\d) to (\\d)$");

    public static CraneInstruction of(String instruction) {
        var matcher = PATTERN.matcher(instruction);
        if (matcher.find()) {
            var numCrates = Integer.parseInt(matcher.group(1));
            var origin = Integer.parseInt(matcher.group(2));
            var destination = Integer.parseInt(matcher.group(3));
            return new CraneInstruction(numCrates, origin, destination);
        } else {
            throw new IllegalArgumentException(String.format("Wrong format for instruction: %s", instruction));
        }
    }
}
