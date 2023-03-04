package com.challenge.aoc2022.day3;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.challenge.aoc2022.day3.RuckSackCompartment.builder;
import static org.assertj.core.api.Assertions.assertThat;

public class RuckSackCompartmentTest {
    @Test
    public void creationTest() {
        final var items = "aAbBcC";
        List<Character> expecteItemList = List.of('a', 'A', 'b', 'B', 'c', 'C');

        var compartment = builder().items(items).build();

        assertThat(compartment.getItems())
                .containsExactlyElementsOf(expecteItemList);
    }

    @Test (dataProvider = "itemsSearchTestData")
    public void testDoesItemExist(String items, char item, boolean expected) {
        var compartment = builder().items(items).build();
        assertThat(compartment.doesItemExist(item)).isEqualTo(expected);
    }

    @DataProvider(name = "itemsSearchTestData")
    private Iterator<Object[]> getItemsSearchTestData() {
        return Arrays.asList(new Object[][] {
                {"abc", 'a', true},
                {"abc", 'd', false},
                {"aBc", 'b', false}
        }).listIterator();
    }
}