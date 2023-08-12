package com.challenge.aoc2022.input;

public class InputFileDataLoaderForTest extends InputFileDataLoader {
    public InputFileDataLoaderForTest(int day, String fileName) {
        super(day, fileName, Environment.TEST);
    }
}
