package com.challenge.aoc2022.day4;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class AssignmentPairTest {

    @Test(dataProvider = "assignmentPairTestData")
    public void testIsARangeContainedIntoTheOther(String ranges, boolean expectedResult) {
        var assignmentPair = AssignmentPair.ofText(ranges);

        assertThat(assignmentPair.isARangeContainedIntoTheOther()).isEqualTo(expectedResult);
    }

    @DataProvider
    private Iterator<Object[]> assignmentPairTestData() {
        return Arrays.asList(new Object[][] {
                {"1-5,2-4", true},
                {"1-5,2-6", false},
                {"1-2,3-4", false},
                {"2-2,2-4", true}
        }).iterator();
    }
}