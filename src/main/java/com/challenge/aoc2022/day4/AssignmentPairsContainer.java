package com.challenge.aoc2022.day4;

import com.challenge.aoc2022.day4.exception.AssignmentPairsCompartmentException;
import com.challenge.library.files.TextFileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class AssignmentPairsContainer {
    private final List<AssignmentPair> assignmentPairs;

    private AssignmentPairsContainer(List<AssignmentPair> assignmentPairs) {
        this.assignmentPairs = assignmentPairs;
    }

    public int calculateNumberOfSelfContainedAssignmentPairs() {
        return (int) assignmentPairs.stream()
                .filter(AssignmentPair::isARangeContainedIntoTheOther)
                .count();
    }

    public int calculateNumberOfOverlappingAssignmentPairs() {
        return (int) assignmentPairs.stream()
                .filter(AssignmentPair::areRangesOverlapped)
                .count();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private static final Path RESOURCE_PATH = Path.of("resources", "com", "challenge", "aoc2022", "day4");
        private Path path = Path.of("src", "main").resolve(RESOURCE_PATH);
        private List<String> assignmentPairsTextItems;

        public Builder forTest() {
            path = Path.of("src", "test").resolve(RESOURCE_PATH);
            return this;
        }

        public Builder fromFile(String filename) throws AssignmentPairsCompartmentException {
            try {
                this.assignmentPairsTextItems = TextFileReader.readAllLinesFromFile(path.resolve(filename));
            } catch (IOException e) {
                throw new AssignmentPairsCompartmentException("Cannot load assignment pairs from file", e);
            }
            return this;
        }

        public AssignmentPairsContainer build() {
            var assignmentPairs = assignmentPairsTextItems.stream()
                    .map(AssignmentPair::ofText)
                    .toList();

            return new AssignmentPairsContainer(assignmentPairs);
        }
    }
}
