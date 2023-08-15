package com.challenge.aoc2022.day6;

import com.challenge.library.string.StringUtils;

public record CharacterGroup(String chars, int position) {
    public static CharacterGroup of(String chars, int position) {
        return new CharacterGroup(chars, position);
    }

    public boolean allCharactersUnique() {
        return StringUtils.stringToCharList(chars).stream()
                .distinct()
                .count() == chars.length();
    }
}
