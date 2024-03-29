package com.challenge.aoc2022.day3;

import com.challenge.aoc2022.day3.exception.RuckSackException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;
import java.util.stream.Stream;

import static com.challenge.aoc2022.day3.RuckSackCompartment.Builder.ofItems;

public class RuckSack {
    private static final Logger logger = LogManager.getLogger();
    private final RuckSackCompartment compartment1;
    private final RuckSackCompartment compartment2;

    private RuckSack(RuckSackCompartment compartment1, RuckSackCompartment compartment2) {
        this.compartment1 = compartment1;
        this.compartment2 = compartment2;
    }

    public int calculatePriorityOfRepeatedElement() throws RuckSackException {
        return compartment1.getItems().stream()
                .filter(compartment2::doesItemExist)
                .peek(p -> logger.debug(String.format("Found repeated item '%c' for rucksack %s", p, this)))
                .findFirst()
                .map(this::getItemPriority)
                .orElseThrow(() -> new RuckSackException(this));
    }

    public boolean hasItem(Character item) {
        return stream().anyMatch(c -> c.equals(item));
    }

    public Stream<Character> stream() {
        return Stream.of(compartment1.stream(), compartment2.stream())
                .flatMap(Function.identity());
    }

    private int getItemPriority(char item) {
        if (item >= 'a' && item <= 'z') {
            return item - 'a' + 1;
        } else {
            return item - 'A' + 27;
        }
    }

    public RuckSackCompartment getCompartment1() {
        return compartment1;
    }

    public RuckSackCompartment getCompartment2() {
        return compartment2;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "("
                + compartment1 + " | "
                + compartment2 + ");";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RuckSack ruckSack = (RuckSack) o;

        return new EqualsBuilder().append(compartment1, ruckSack.compartment1).append(compartment2, ruckSack.compartment2).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(compartment1).append(compartment2).toHashCode();
    }

    public static class Builder {
        private String stringItems;

        public static RuckSack ofCompartmentItems(String items) {
            return builder()
                    .items(items)
                    .build();
        }

        public Builder items(String items) {
            checkItems(items);
            this.stringItems = items;
            return this;
        }

        public RuckSack build() {
            final var itemsCompartment1 = stringItems.substring(0, stringItems.length()/2);
            final var itemsCompartment2 = stringItems.substring(stringItems.length()/2);

            return new RuckSack(ofItems(itemsCompartment1), ofItems(itemsCompartment2));
        }

        private void checkItems(String items) {
            if (items.isEmpty()) {
                throw new IllegalArgumentException("Some items must be provided");
            }

            if (items.length() % 2 != 0) {
                throw new IllegalArgumentException(String.format("Wrong number of items (%d). An even number of items should be provided", items.length()));
            }
        }
    }
}
