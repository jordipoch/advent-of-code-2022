package com.challenge.aoc2022.day8;

import com.challenge.aoc2022.input.InputDataLoader;
import com.challenge.library.string.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.List;
import java.util.function.Supplier;

@Singleton
public class TreeHeightsSupplier implements Supplier<List<Integer>> {
    private final InputDataLoader dataLoader;

    @Inject
    public TreeHeightsSupplier(InputDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }
    @Override
    public List<Integer> get() {
        return dataLoader.loadData().stream()
                .map(StringUtils::stringToDigitList)
                .flatMap(List::stream)
                .toList();
    }
}
