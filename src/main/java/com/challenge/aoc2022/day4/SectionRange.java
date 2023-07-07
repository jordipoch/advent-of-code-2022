package com.challenge.aoc2022.day4;

public final class SectionRange {
    private final int min;
    private final int max;

    public SectionRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static SectionRange ofText(String text) {
        var rangeParts = text.split("-");
        return new SectionRange(Integer.parseInt(rangeParts[0]), Integer.parseInt(rangeParts[1]));
    }

    public boolean contains(SectionRange another) {
        return min <= another.min && max >= another.max;
    }

    public boolean overlapsWith(SectionRange another) {
        return contains(another) || another.contains(this) ||
                containsValue(another.min) || containsValue(another.max);
    }

    private boolean containsValue(int value) {
        return value >= min && value <= max;
    }

    @Override
    public String toString() {
        return '[' +
                "min=" + min + ", " +
                "max=" + max + ']';
    }

}
