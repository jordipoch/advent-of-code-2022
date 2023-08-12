package com.challenge.aoc2022.input;

public class InputFileDataLoaderForProd extends InputFileDataLoader {
    public InputFileDataLoaderForProd(int day, String fileName) {
        super(day, fileName, Environment.PRODUCTION);
    }
}
