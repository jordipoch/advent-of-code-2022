package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.day5.exception.ShipSuppliesCreationException;
import com.challenge.aoc2022.day5.exception.ShipSuppliesFileParserException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class ShipSupplies {
    private final List<StackOfCrates> crates;

    public ShipSupplies(List<StackOfCrates> crates) {
        this.crates = crates;
    }

    public Character popCrateFromStack(int stackNum) {
        checkStackNumber(stackNum);
        checkStackNotEmpty(stackNum);

        return crates.get(stackNum - 1).popCrate();
    }

    public void pushCrateToStack(Character crate, int stackNum) {
        checkStackNumber(stackNum);

        crates.get(stackNum - 1).pushCrate(crate);
    }

    public String getTopCratesFromStacks() {
        return crates.stream()
                .filter(not(StackOfCrates::isEmpty))
                .map(StackOfCrates::peekCrate)
                .map(Objects::toString)
                .collect(Collectors.joining());
    }


    private void checkStackNumber(int stackNum) {
        if (stackNum < 1 || stackNum > crates.size()) {
            throw new IllegalArgumentException(String.format("Wrong stack number (%d). Expecting a value in range [1, %d]", stackNum, crates.size()));
        }
    }

    private void checkStackNotEmpty(int stackNum) {
        if (crates.get(stackNum - 1).isEmpty()) {
            throw new IllegalStateException(String.format("The stack number %d is empty!", stackNum));
        }
    }

    int getNumOfStacks() {
        return crates.size();
    }
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return Printer.print(crates);
    }

    public static class Builder {
        private static final Path RESOURCE_PATH = Path.of("resources", "com", "challenge", "aoc2022", "day5");
        private static final Path TEST = Path.of("src", "test");
        private Path path = Path.of("src", "main").resolve(RESOURCE_PATH);
        private List<StackOfCrates> crates = new ArrayList<>();


        public Builder addStackOfCrates(String cratesAsString) {
            this.crates.add(StackOfCrates.of(cratesAsString));
            return this;
        }

        public Builder forTest() {
            path = TEST.resolve(RESOURCE_PATH);
            return this;
        }
        public Builder fromFile(Path filePath) throws ShipSuppliesCreationException {
            var parser = ShipSuppliesFileParser.of(filePath);
            try {
                crates = parser.parse().stream()
                        .map(StackOfCrates::of)
                        .toList();
            } catch (ShipSuppliesFileParserException e) {
                throw new ShipSuppliesCreationException("Error creating ship supplies from file", e);
            }
            return this;
        }

        public ShipSupplies build() {
            return new ShipSupplies(crates);
        }
    }

    private static class Printer {
        private static final String EMPTY_CRATE = "   ";
        private static String print(List<StackOfCrates> crates) {
            return String.join("", createCratesLayers(crates)) +
                    createCratesPositionsLayer(crates);
        }

        private static List<String> createCratesLayers(List<StackOfCrates> crates) {
            var stringBuilders = Stream.generate(StringBuilder::new)
                    .limit(getSizeOfLargestStackOfCrates(crates))
                    .toList();

            for (var stackOfCrates : crates) {
                addFromStackOfCrates(stringBuilders, stackOfCrates);
            }

            List<String> layersList = stringBuilders.stream()
                    .map(sb -> sb.deleteCharAt(sb.length() - 1))
                    .map(StringBuilder::toString)
                    .map(s -> s.concat("\n"))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            Collections.reverse(layersList);
            return layersList;
        }

        private static String createCratesPositionsLayer(List<StackOfCrates> crates) {
            var s = IntStream.rangeClosed(1, crates.size())
                    .mapToObj(i -> " " + i + "  ")
                    .collect(Collectors.joining());

            return s.substring(0, s.length()-1);
        }

        private static int getSizeOfLargestStackOfCrates(List<StackOfCrates> crates) {
            return crates.stream()
                    .mapToInt(StackOfCrates::size)
                    .max()
                    .orElse(0);
        }

        private static void addFromStackOfCrates(List<StringBuilder> stringBuilders, StackOfCrates stackOfCrates) {
            var iterator = stackOfCrates.getCrateListInReversOrder().listIterator();
            for (StringBuilder currentLayer : stringBuilders) {
                if (iterator.hasNext()) {
                    currentLayer.append(getPrintableCrate(iterator.next()));
                } else {
                    currentLayer.append(EMPTY_CRATE);
                }
                currentLayer.append(' ');
            }
        }

        private static String getPrintableCrate(Character crate) {
            return "[" + crate + "]";
        }
    }
}
