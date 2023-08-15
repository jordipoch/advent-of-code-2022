package com.challenge.library.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringUtils {
    private StringUtils() {
    }

    public static List<Character> stringToCharList(String s) {
        Objects.requireNonNull(s);
        return s.chars()
                .mapToObj(i -> (char) i)
                .toList();
    }

    public static List<String> partitionStringBySize(String s, int chunkSize) {
        Objects.requireNonNull(s);
        if (chunkSize <= 0)
            throw new IllegalArgumentException(String.format("Wrong chunk size (%d). It should be > 0", chunkSize));

        var result = new ArrayList<String>();
        var currentPosition = 0;

        while (currentPosition < s.length()) {
            var endPosition = Math.min(currentPosition + chunkSize, s.length());
            result.add(s.substring(currentPosition, endPosition));

            currentPosition += chunkSize;
        }

        return result;
    }
}
