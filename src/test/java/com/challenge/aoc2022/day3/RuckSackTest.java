package com.challenge.aoc2022.day3;

import com.challenge.aoc2022.day3.exception.RuckSackException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.challenge.aoc2022.day3.RuckSack.Builder.ofCompartmentItems;
import static com.challenge.library.lists.ListUtils.charListToString;
import static org.assertj.core.api.Assertions.assertThat;

public class RuckSackTest {
    private static final String COMPARTMENT_1 = "vJrwpWtwJgWr";
    private static final String COMPARTMENT_2 = "hcsFMMfFFhFp";

    private static final RuckSack DEFAULT_RUCK_SACK = ofCompartmentItems(COMPARTMENT_1 + COMPARTMENT_2);
    @BeforeMethod
    public void setUp() {

    }

    @Test
    public void ruckSackCreationTest() {
        final var ruckSack = RuckSack.builder()
                .items(COMPARTMENT_1 + COMPARTMENT_2)
                .build();

        checkItems(ruckSack.getCompartment1().getItems(), COMPARTMENT_1);
        checkItems(ruckSack.getCompartment2().getItems(), COMPARTMENT_2);
    }

    @Test (dataProvider = "priorityCalculationTestData")
    public void calculatePriorityOfRepeatedElementTest(String items, int expectedPriority) throws RuckSackException {
        final var rucksack = ofCompartmentItems(items);
        assertThat(rucksack.calculatePriorityOfRepeatedElement()).isEqualTo(expectedPriority);
    }

    @Test (dataProvider = "hasItemTestData")
    public void hasItemTest(String ruckSackItems, char itemToFind, boolean expected) {
        final var ruckSack = ofCompartmentItems(ruckSackItems);

        assertThat(ruckSack.hasItem(itemToFind)).isEqualTo(expected);
    }

    @Test
    public void streamTest() {
        final var expected = DEFAULT_RUCK_SACK;

        final var ruckSack = RuckSack.builder()
                .items(expected.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
                .build();

        assertThat(ruckSack).isEqualTo(expected);
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

    @DataProvider(name = "hasItemTestData")
    private Iterator<Object[]> getHasItemTestData() {
        return Arrays.asList(new Object[][] {
                {"abaB", 'a', true},
                {"aBAB", 'B', true},
                {"abCd", 'c', false},
                {"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", 'j', true},
                {"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", 'b', false}
        }).listIterator();
    }
}