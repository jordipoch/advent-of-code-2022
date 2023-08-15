package com.challenge.aoc2022.input;

import com.challenge.aoc2022.input.exception.DataLoadingException;
import com.challenge.library.files.TextFileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public abstract class InputFileDataLoader implements InputDataLoader {
    private static final Path BASE_PATH = Path.of("resources", "com", "challenge", "aoc2022");

    private final Path filePath;

    protected InputFileDataLoader(int day, String fileName, Environment environment) {
        filePath = environment.getResourcePath().resolve(BASE_PATH).resolve(createFolderForDay(day)).resolve(fileName);
    }

    @Override
    public List<String> loadData() {
        try {
            return TextFileReader.readAllLinesFromFile(filePath);
        } catch (IOException e) {
            throw new DataLoadingException(String.format("Error loading data from file %s", filePath.getFileName().toString()),  e);
        }
    }

    private String createFolderForDay(int day) {
        return "day" + day;
    }
}
