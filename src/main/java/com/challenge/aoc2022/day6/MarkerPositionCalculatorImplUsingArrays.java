package com.challenge.aoc2022.day6;

public class MarkerPositionCalculatorImplUsingArrays implements MarkerPositionCalculator {
    @Override
    public int calculateMarkerPosition(String data) {
        if (data.length() >= MARKER_SIZE) {
            char[] chars = data.toCharArray();
            for (int i = MARKER_SIZE; i <= chars.length; i++) {
                if (areAllCharactersDifferent(chars, i - MARKER_SIZE, i))
                    return i;
            }
        }
        return 0;
    }

    private boolean areAllCharactersDifferent(char[] chars, int startPosIncl, int endPosExcl) {
        for (int i = startPosIncl; i < endPosExcl - 1; i++)
            if (characterExistsInRange(chars, chars[i], i+1, endPosExcl))
                return false;

        return true;
    }

    private boolean characterExistsInRange(char[] chars, char c, int startPosIncl, int endPosExcl) {
        for (int i = startPosIncl; i < endPosExcl; i++)
            if (chars[i] == c)
                return true;

        return false;
    }
}
