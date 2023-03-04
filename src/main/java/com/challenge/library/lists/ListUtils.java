package com.challenge.library.lists;

import java.util.List;

public class ListUtils {
    private ListUtils() {
    }

    public static String charListToString(List<Character> list) {
        return list.stream()
                .reduce("", (s, c) -> s + c, String::concat);
    }
}
