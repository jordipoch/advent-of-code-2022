package com.challenge.aoc2022.day2;

import com.challenge.aoc2022.day2.exception.RockPaperScissorsGameException;
import com.challenge.library.files.TextFileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class RockPaperScissorsGame {
    private final List<Play> plays;

    public RockPaperScissorsGame(List<Play> plays) {
        this.plays = plays;
    }

    public int calculateGameScore() {
        return plays.stream()
                .mapToInt(Play::getScore)
                .sum();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "plays=" + plays.toString();
    }

    public static class Builder {
        private static final String SPACE = " ";
        private static final Path RESOURCE_PATH = Path.of("resources", "com", "challenge", "aoc2022", "day2");
        List<String> stringPlays;
        private Path path = Path.of("src", "main").resolve(RESOURCE_PATH);

        public static RockPaperScissorsGame createFrom(String... plays) {
            return RockPaperScissorsGame.builder()
                    .fromPlaysList(List.of(plays))
                    .build();
        }

        public Builder fromPlaysList(List<String> plays) {
            this.stringPlays = plays;
            return this;
        }

        public Builder isTest() {
            path = Path.of("src", "test").resolve(RESOURCE_PATH);
            return this;
        }

        public Builder fromFile(String fileName) throws RockPaperScissorsGameException {
            try {
                this.stringPlays = TextFileReader.readAllLinesFromFile(path.resolve(fileName));
                return this;
            } catch (IOException e) {
                throw new RockPaperScissorsGameException("Error creating game. Cannot read plays from file", e);
            }
        }

        public RockPaperScissorsGame build() {
            return new RockPaperScissorsGame(createPlayList());
        }

        private List<Play> createPlayList() {
            return stringPlays.stream()
                    .map(s -> s.split(SPACE))
                    .map(a -> Play.of(a[0], a[1]))
                    .toList();
        }
    }
}
