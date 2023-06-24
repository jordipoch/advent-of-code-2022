package com.challenge.aoc2022.day3;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class RuckSackGroupTest {
    private static final String[] RUCKSACKS_GROUP_1 = {"vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg"};
    private static final String[] RUCKSACKS_GROUP_2 = {"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw"};


    @Test
    public void ruckSackGroupCreationTest() {
        var ruckSacks = RUCKSACKS_GROUP_1;

        var ruckSackGroup = createRuckSackGroup(ruckSacks);

        var expectedRuckSackList = Arrays.stream(ruckSacks)
                .map(RuckSack.Builder::ofCompartmentItems)
                .toList();

        assertThat(ruckSackGroup.getRuckSacks()).containsExactlyElementsOf(expectedRuckSackList);
    }

    @Test (dataProvider = "Badge priority test data")
    public void getBadgePriorityTest(String[] ruckSacks, int expectedPriority) {
        var ruckSackGroup = createRuckSackGroup(ruckSacks);

        int badgePriority = ruckSackGroup.getBadgePriority();

        assertThat(badgePriority).isEqualTo(expectedPriority);
    }

    private RuckSackGroup createRuckSackGroup(String... ruckSacks) {
        var builder = RuckSackGroup.builder();
        for (var ruckSack : ruckSacks) {
            builder.withRuckSack(ruckSack);
        }
        return builder.build();
    }

    @DataProvider(name = "Badge priority test data")
    private Iterator<Object[]> getBadgePriorityTestData() {
        return Arrays.asList(new Object[][] {
                {RUCKSACKS_GROUP_1, 18},
                {RUCKSACKS_GROUP_2, 52}
        }).listIterator();
    }
}