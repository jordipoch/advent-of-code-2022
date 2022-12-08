package com.challenge.library.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextFileReader {
    private TextFileReader() {
    }

    public static String readFirstLineFromFile(Path filePath) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.toFile()))) {
            return bufferedReader.readLine();
        }
    }

    public static List<String> readAllLinesFromFile(Path filePath) throws IOException {
        try (var lines = Files.lines(filePath)) {
            return lines.toList();
        }
    }
}
