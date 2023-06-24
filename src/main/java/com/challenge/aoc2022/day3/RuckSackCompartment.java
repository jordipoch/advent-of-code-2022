package com.challenge.aoc2022.day3;

import com.challenge.library.lists.ListUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.challenge.library.lists.ListUtils.charListToString;

public class RuckSackCompartment {
    private final List<Character> items;

    private RuckSackCompartment(List<Character> items) {
        this.items = items;
    }

    public List<Character> getItems() {
        return Collections.unmodifiableList(items);
    }

    public boolean doesItemExist(char item) {
        return items.stream().anyMatch(c -> c.equals(item));
    }

    public Stream<Character> stream() {
        return items.stream();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return charListToString(items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuckSackCompartment that = (RuckSackCompartment) o;
        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    public static class Builder {
        private String stringItems;

        public Builder items(String stringItems) {
            this.stringItems = stringItems;
            return this;
        }

        public static RuckSackCompartment ofItems(String stringItems) {
            return builder()
                    .items(stringItems)
                    .build();
        }

        public RuckSackCompartment build() {
            return new RuckSackCompartment(createItemList());
        }

        private List<Character> createItemList() {
            return stringItems.chars()
                    .mapToObj(i -> (char) i)
                    .toList();
        }
    }
}
