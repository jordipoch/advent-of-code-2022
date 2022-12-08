package com.challenge.library.lists;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static com.challenge.library.lists.ListSplitter.splitAndConvertList;
import static com.challenge.library.lists.ListSplitter.splitList;
import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

public class ListSplitterTest {
    @Test
    public void basicTestWithStrings() {
        List<String> testList = of("1", "2", "|", "3", "4");
        var result = splitList(testList, "|");
        assertThat(result).containsExactly(
                of("1", "2"), of("3", "4")
        );
    }

    @Test
    public void withNoDividersTheListOnlyContainsTheInputList() {
        List<String> testList = of("1", "2", "3", "4");
        var result = splitList(testList, "|");
        assertThat(result).containsExactly(testList);
    }

    @Test
    public void withEmptyListAnEmptyListShouldBeReturned() {
        List<Integer> emptyList = emptyList();
        assertThat(splitList(emptyList, 23)).isEmpty();
    }

    @Test
    public void withOnlyDividerElementsListOfEmptyListsShouldBeReturned() {
        List<String> testList = of("", "");
        var result = splitList(testList, "");
        assertThat(result)
                .hasSize(3)
                .containsOnly(Collections.emptyList());
    }

    @Test
    public void testSplitAndConvertList() {
        List<String> testList = of("1", "2", "|", "3", "4");
        var result = splitAndConvertList(testList, "|", Integer::parseInt);
        assertThat(result).containsExactly(
                of(1, 2), of(3, 4)
        );
    }

}