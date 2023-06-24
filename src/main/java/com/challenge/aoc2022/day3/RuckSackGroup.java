package com.challenge.aoc2022.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.challenge.aoc2022.day3.RuckSack.Builder.ofCompartmentItems;

public class RuckSackGroup {
    private final List<RuckSack> ruckSacks;

    public RuckSackGroup(List<RuckSack> ruckSacks) {
        this.ruckSacks = ruckSacks;
    }

    public List<RuckSack> getRuckSacks() {
        return new ArrayList<>(ruckSacks);
    }

    public int getBadgePriority() {
        return ruckSacks.get(0).stream()
                .filter(c -> ruckSacks.stream().skip(1)
                        .allMatch(ruckSack -> ruckSack.hasItem(c)))
                .findFirst()
                .map(this::getItemPriority)
                .orElse(-1);
    }

    private int getItemPriority(char item) {
        if (item >= 'a' && item <= 'z') {
            return item - 'a' + 1;
        } else {
            return item - 'A' + 27;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<RuckSack> ruckSacks = new ArrayList<>();

        public static RuckSackGroup createRuckSackGroup(List<RuckSack> ruckSacks) {
            return new Builder()
                    .withRuckSacks(ruckSacks)
                    .build();
        }

        public Builder withRuckSack(String ruckSack) {
            ruckSacks.add(ofCompartmentItems(ruckSack));
            return this;
        }

        private Builder withRuckSacks(String... ruckSacksArray) {
            ruckSacks.addAll(Arrays.stream(ruckSacksArray)
                    .map(RuckSack.Builder::ofCompartmentItems)
                    .toList());
            return this;
        }

        private Builder withRuckSacks(List<RuckSack> ruckSacks) {
            this.ruckSacks.addAll(ruckSacks);
            return this;
        }

        public RuckSackGroup build() {
            return new RuckSackGroup(ruckSacks);
        }
    }
}
