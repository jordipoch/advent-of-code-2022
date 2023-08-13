package com.challenge.aoc2022.day5.main;

import com.challenge.aoc2022.day5.ShipSupplies;
import com.challenge.aoc2022.day5.main.config.Supplies;
import com.challenge.aoc2022.input.InputDataLoader;
import com.challenge.library.string.StringUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ShipSuppliesSupplier implements Supplier<ShipSupplies> {
    private static final Character EMPTY_CRATE = '_';
    public static final String CRATE_REGEX = "^\\[(\\p{Upper})]\\s?$";

    private final InputDataLoader dataLoader;

    @Inject
    public ShipSuppliesSupplier(@Supplies InputDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public ShipSupplies get() {
        List<String> dataLines = dataLoader.loadData();
        var stacksOfCrates = convertToStacksOfCrates(dataLines);

        var shipSuppliesBuilder = ShipSupplies.builder();
        stacksOfCrates.forEach(shipSuppliesBuilder::addStackOfCrates);

        return shipSuppliesBuilder.build();
    }

    private List<String> convertToStacksOfCrates(List<String> dataLines) {
        var shipSuppliesBuilders = createBuilders(dataLines);
        fillBuilders(dataLines, shipSuppliesBuilders);

        return convertToResult(shipSuppliesBuilders);
    }

    private List<StringBuilder> createBuilders(List<String> lines) {
        var num = getNumberOfStackOfCrates(lines);
        return Stream.generate(StringBuilder::new).limit(num).toList();
    }

    private void fillBuilders(List<String> lines, List<StringBuilder> shipSuppliesBuilders) {
        var linesWithCrates = lines.subList(0, lines.size() - 1);
        linesWithCrates.stream()
                .map(this::extractCratesFromLine)
                .forEach(crates -> addCratesToBuilders(crates, shipSuppliesBuilders));
    }

    private static List<String> convertToResult(List<StringBuilder> shipSuppliesBuilders) {
        return shipSuppliesBuilders.stream()
                .map(StringBuilder::toString)
                .toList();
    }

    private int getNumberOfStackOfCrates(List<String> lines) {
        var legendLine = lines.get(lines.size() - 1);
        return legendLine.trim().split("\\s+").length;
    }

    private void addCratesToBuilders(List<Character> crates, List<StringBuilder> builders) {
        ListIterator<StringBuilder> iterator = builders.listIterator();
        for (Character crate : crates) {
            StringBuilder builder = iterator.next();
            if (!EMPTY_CRATE.equals(crate))
                builder.append(crate);
        }
    }

    private List<Character> extractCratesFromLine(String line) {
        return StringUtils.partitionStringBySize(line, 4).stream()
                .map(this::extractCrateFromString)
                .toList();
    }

    private Character extractCrateFromString(String s) {
        var pattern = Pattern.compile(CRATE_REGEX);
        var matcher = pattern.matcher(s);
        if (matcher.find()) {
            return matcher.group(1).charAt(0);
        } else {
            return EMPTY_CRATE;
        }
    }
}
