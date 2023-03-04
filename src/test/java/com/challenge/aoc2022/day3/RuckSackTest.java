package com.challenge.aoc2022.day3;

import com.challenge.aoc2022.day3.exception.RuckSackException;
import com.challenge.library.lists.ListUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.challenge.aoc2022.day3.RuckSack.Builder.ofCompartmentItems;
import static com.challenge.library.lists.ListUtils.charListToString;
import static org.assertj.core.api.Assertions.assertThat;

public class RuckSackTest {
    @Test
    public void ruckSackCreationTest() {
        final var itemsCompartment1 = "vJrwpWtwJgWr";
        final var itemsCompartment2 = "hcsFMMfFFhFp";

        var ruckSack = RuckSack.builder()
                .items(itemsCompartment1 + itemsCompartment2)
                .build();

        checkItems(ruckSack.getCompartment1().getItems(), itemsCompartment1);
        checkItems(ruckSack.getCompartment2().getItems(), itemsCompartment2);
    }

    @Test (dataProvider = "priorityCalculationTestData")
    public void calculatePriorityOfRepeatedElementTest(String items, int expectedPriority) throws RuckSackException {
        var rucksack = ofCompartmentItems(items);
        assertThat(rucksack.calculatePriorityOfRepeatedElement()).isEqualTo(expectedPriority);
    }

    private void checkItems(List<Character> itemsList, String expectedItems) {
        var actualItems = charListToString(itemsList);
        assertThat(actualItems).isEqualTo(expectedItems);
    }

    @DataProvider(name = "priorityCalculationTestData")
    private Iterator<Object[]> getPriorityCalculationTestData() {
        return Arrays.asList(new Object[][] {
                {"abaB", 1},
                {"aBAB", 28},
                {"aCDaFFomBg", 32},
                {"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", 38}
        }).listIterator();
    }
}