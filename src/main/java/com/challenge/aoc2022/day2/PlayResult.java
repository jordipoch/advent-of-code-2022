package com.challenge.aoc2022.day2;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PlayResult {
    LOSE("X"), DRAW("Y"), WIN("Z");

    private static final Map<String, PlayResult> MAP_BY_CODE = Arrays.stream(PlayResult.values())
            .collect(Collectors.toMap(PlayResult::getCode, Function.identity()));
    private final String code;

    PlayResult(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PlayResult fromCode(String code) {
        return MAP_BY_CODE.get(code);
    }
}
