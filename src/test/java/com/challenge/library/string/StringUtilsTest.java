package com.challenge.library.string;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class StringUtilsTest {

    @Test
    public void testPartitionStringBySize() {
        Assertions.assertThat(StringUtils.partitionStringBySize("ABCD", 1)).containsExactly("A", "B", "C", "D");
        Assertions.assertThat(StringUtils.partitionStringBySize("ABCD", 2)).containsExactly("AB", "CD");
        Assertions.assertThat(StringUtils.partitionStringBySize("ABCDEFG", 3)).containsExactly("ABC", "DEF", "G");
    }

    @Test
    public void testPartitionStringBySizeCornerCases() {
        Assertions.assertThat(StringUtils.partitionStringBySize("", 2)).isEmpty();
        Assertions.assertThat(StringUtils.partitionStringBySize("AB", 4)).containsExactly("AB");
    }

    @Test
    public void testPartitionStringBySizeShouldThrowExceptionWhenEmptyString() {
        assertThatNullPointerException().isThrownBy(() -> StringUtils.partitionStringBySize(null, 2));
    }

    @Test
    public void testPartitionStringBySizeShouldThrowExceptionWhenInvalidSize() {
        assertThatIllegalArgumentException().isThrownBy(() -> StringUtils.partitionStringBySize("AB", 0));
    }
}