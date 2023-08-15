package com.challenge.library.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class StringUtilsTest {

    @Test
    public void testPartitionStringBySize() {
        assertThat(StringUtils.partitionStringBySize("ABCD", 1)).containsExactly("A", "B", "C", "D");
        assertThat(StringUtils.partitionStringBySize("ABCD", 2)).containsExactly("AB", "CD");
        assertThat(StringUtils.partitionStringBySize("ABCDEFG", 3)).containsExactly("ABC", "DEF", "G");
    }

    @Test
    public void testPartitionStringBySizeCornerCases() {
        assertThat(StringUtils.partitionStringBySize("", 2)).isEmpty();
        assertThat(StringUtils.partitionStringBySize("AB", 4)).containsExactly("AB");
    }

    @Test
    public void testPartitionStringBySizeShouldThrowExceptionWhenEmptyString() {
        assertThatNullPointerException().isThrownBy(() -> StringUtils.partitionStringBySize(null, 2));
    }

    @Test
    public void testPartitionStringBySizeShouldThrowExceptionWhenInvalidSize() {
        assertThatIllegalArgumentException().isThrownBy(() -> StringUtils.partitionStringBySize("AB", 0));
    }

    @Test (dataProvider = "data for stringToCharList test")
    public void testStringToCharList(String s, List<Character> expectedResult) {
        assertThat(StringUtils.stringToCharList(s)).containsExactlyElementsOf(expectedResult);
    }

    @Test
    public void testStringToCharListWhenNullString() {
        assertThatNullPointerException().isThrownBy(() -> StringUtils.stringToCharList(null));
    }

    @DataProvider(name = "data for stringToCharList test")
    protected Iterator<Object[]> getStringToCharListTestData() {
        return Arrays.asList(new Object[][] {
                {"", Collections.emptyList()},
                {"abc", List.of('a', 'b', 'c')},
                {"aabbcc123", List.of('a', 'a', 'b', 'b', 'c', 'c', '1', '2', '3')}
        }).iterator();
    }
}