package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.day5.exception.ShipSuppliesFileParserException;
import com.challenge.library.files.TextFileReader;
import com.challenge.library.string.StringUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ShipSuppliesFileParser {

    private static final Character EMPTY_CRATE = '_';
    public static final String CRATE_REGEX = "^\\[(\\p{Upper})]\\s?$";
    private final Path filePath;
    private ShipSuppliesFileParser(Path filePath) {
        this.filePath = filePath;
    }

    public static ShipSuppliesFileParser of(Path filePath) {
        return new ShipSuppliesFileParser(filePath);
    }
    public List<String> parse() throws ShipSuppliesFileParserException {
        var lines = readFromFile();

        var shipSuppliesBuilders = createBuilders(lines);
        fillBuilders(lines, shipSuppliesBuilders);

        return convertToResult(shipSuppliesBuilders);
    }

    private List<String> readFromFile() throws ShipSuppliesFileParserException {
        try {
            return TextFileReader.readAllLinesFromFile(filePath);
        } catch (IOException e) {
            throw new ShipSuppliesFileParserException(String.format("Cannot read file %s", filePath.getFileName().toString()), e);
        }
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
        ListIterator<StringBuilder>  iterator = builders.listIterator();
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
