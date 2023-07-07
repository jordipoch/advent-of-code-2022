package com.challenge.aoc2022.day4;

public class AssignmentPair {
    private final SectionRange range1;
    private final SectionRange range2;

    public AssignmentPair(SectionRange range1, SectionRange range2) {
        this.range1 = range1;
        this.range2 = range2;
    }

    public static AssignmentPair ofText(String text) {
        var parts = text.split(",");
        return new AssignmentPair(SectionRange.ofText(parts[0]), SectionRange.ofText(parts[1]));
    }

    public boolean isARangeContainedIntoTheOther() {
        return range1.contains(range2) || range2.contains(range1);
    }

    public boolean areRangesOverlapped() {
        return range1.overlapsWith(range2);
    }
}
