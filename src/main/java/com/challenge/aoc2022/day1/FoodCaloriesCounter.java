package com.challenge.aoc2022.day1;

import com.challenge.aoc2022.day1.exception.FoodCaloriesCounterException;
import com.challenge.library.files.TextFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static com.challenge.library.lists.ListSplitter.splitAndConvertList;

public class FoodCaloriesCounter {
    private static final Logger logger = LogManager.getLogger();
    private final List<List<Integer>> calories;

    private FoodCaloriesCounter(List<List<Integer>> calories) {
        this.calories = calories;
    }

    public int calculateMax() {
        logger.debug("Calculating max calories from calory list: {}", calories);

        return calories.stream()
                .mapToInt(l -> l.stream().mapToInt(Integer::intValue).sum())
                .max()
                .orElse(0);
    }

    public int calculateTopNSum(int n) throws FoodCaloriesCounterException {
        checkN(n);

        return calories.stream()
                .mapToInt(l -> l.stream().mapToInt(Integer::intValue).sum())
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(n)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void checkN(int n) throws FoodCaloriesCounterException {
        if (n <= 0) {
            throw new FoodCaloriesCounterException(String.format("Value provided to calculate Top N sum (%d) should be larger than 0", n));
        }

        if (n > calories.size()) {
            throw new FoodCaloriesCounterException(String.format("Value provided to calculate Top N sum (%d) is larger than list size (%d)", n, calories.size()));
        }
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private static final Path BASE_PATH = Path.of("src", "main", "resources", "com", "challenge", "aoc2022", "day1");
        private static final String EMPTY_LIST = "";
        private List<String> calories;
        public static FoodCaloriesCounter fromList(String... calories) {
            return builder().withCaloriesList(List.of(calories)).build();
        }

        private Builder() {
        }

        public Builder withFilename(String filename) throws FoodCaloriesCounterException {
            this.calories = getCaloriesList(BASE_PATH.resolve(filename));
            return this;
        }

        public Builder withCaloriesList(List<String> calories) {
            this.calories = calories;
            return this;
        }

        public FoodCaloriesCounter build() {
            if (calories == null) {
                throw new IllegalStateException("No calories list or file has been provided");
            }
            return new FoodCaloriesCounter(splitAndConvertList(calories, EMPTY_LIST, Integer::parseInt));
        }

        private List<String> getCaloriesList(Path path) throws FoodCaloriesCounterException {
            try {
                return TextFileReader.readAllLinesFromFile(path);
            } catch (IOException e) {
                throw new FoodCaloriesCounterException("Cannot load the calories list", e);
            }
        }
    }
}
