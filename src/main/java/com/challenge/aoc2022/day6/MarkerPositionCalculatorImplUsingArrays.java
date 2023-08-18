package com.challenge.aoc2022.day6;

public class MarkerPositionCalculatorImplUsingArrays extends AbstractMarkerPositionCalculator {
    public MarkerPositionCalculatorImplUsingArrays(int markerSize) {
        super(markerSize);
    }

    @Override
    public int calculateMarkerPosition(String data) {
        if (data.length() >= markerSize) {
            char[] chars = data.toCharArray();
            for (int i = markerSize; i <= chars.length; i++) {
                if (areAllCharactersDifferent(chars, i - markerSize, i))
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
