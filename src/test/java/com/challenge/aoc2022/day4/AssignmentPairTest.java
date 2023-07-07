package com.challenge.aoc2022.day4;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class AssignmentPairTest {

    @Test(dataProvider = "RangeContainmentTestData")
    public void testIsARangeContainedIntoTheOther(String ranges, boolean expectedResult) {
        var assignmentPair = AssignmentPair.ofText(ranges);

        assertThat(assignmentPair.isARangeContainedIntoTheOther()).isEqualTo(expectedResult);
    }

    @Test(dataProvider = "RangeOverlappingTestData")
    public void testAreRangesOverlapped(String ranges, boolean expectedResult) {
        var assignmentPair = AssignmentPair.ofText(ranges);

        assertThat(assignmentPair.areRangesOverlapped()).isEqualTo(expectedResult);
    }

    @DataProvider
    private Iterator<Object[]> RangeContainmentTestData() {
        return Arrays.asList(new Object[][] {
                {"1-5,2-4", true},
                {"1-5,2-6", false},
                {"1-2,3-4", false},
                {"2-2,2-4", true}
        }).iterator();
    }

    @DataProvider
    private Iterator<Object[]> RangeOverlappingTestData() {
        return Arrays.asList(new Object[][] {
                {"1-5,2-4", true}, // 1st contains 2nd
                {"2-4,1-5", true}, // 2nd contains 1st
                {"2-6,1-5", true}, // 1st's min inside 2nd
                {"1-5,2-6", true}, // 1st's max inside 2nd
                {"1-2,3-4", false}, // 1st before 2nd
                {"3-4,1-2", false}, // 1st after 2nd
        }).iterator();
    }
}