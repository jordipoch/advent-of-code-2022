package com.challenge.aoc2022.day3;

import com.challenge.aoc2022.day3.exception.FoodSuppliesException;
import com.challenge.library.files.TextFileReader;
import org.apache.commons.collections4.ListUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class FoodSupplies {
    private final List<RuckSack> rucksacks;

    public FoodSupplies(List<RuckSack> rucksacks) {
        this.rucksacks = rucksacks;
    }

    public int calculateSumOfPriorities() {
        return rucksacks.stream()
                .mapToInt(RuckSack::calculatePriorityOfRepeatedElement)
                .sum();
    }

    public int calculateSumOfGroupBadgePriorities() {
        var ruckSackGroups = splitInGroups(rucksacks, 3);
        return ruckSackGroups.stream()
                .mapToInt(RuckSackGroup::getBadgePriority)
                .sum();
    }

    private List<RuckSackGroup> splitInGroups(List<RuckSack> rucksacks, int groupSize) {
        return ListUtils.partition(rucksacks, groupSize).stream()
                .map(RuckSackGroup.Builder::createRuckSackGroup)
                .toList();
    }

    public static class Builder {
        private static final Path RESOURCE_PATH = Path.of("resources", "com", "challenge", "aoc2022", "day3");
        private Path path = Path.of("src", "main").resolve(RESOURCE_PATH);
        private List<String> foodSuppliesItems;
        public static Builder aFoodSupplies() {
            return new Builder();
        }

        public Builder test() {
            path = Path.of("src", "test").resolve(RESOURCE_PATH);
            return this;
        }

        public Builder items(String filename) throws FoodSuppliesException {
            try {
                this.foodSuppliesItems = TextFileReader.readAllLinesFromFile(path.resolve(filename));
            } catch (IOException e) {
                throw new FoodSuppliesException("Cannot load food supplies items from file", e);
            }
            return this;
        }

        public FoodSupplies build() {
            final var rucksacks = foodSuppliesItems.stream()
                    .map(RuckSack.Builder::ofCompartmentItems)
                    .toList();
            return new FoodSupplies(rucksacks);
        }
    }
}
