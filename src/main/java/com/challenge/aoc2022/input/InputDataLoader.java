package com.challenge.aoc2022.input;

import java.util.List;

@FunctionalInterface
public interface InputDataLoader {
    List<String> loadData();
}