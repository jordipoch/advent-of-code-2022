package com.challenge.aoc2022.day5.main;

import com.challenge.aoc2022.day5.CraneInstruction;
import com.challenge.aoc2022.input.InputDataLoader;

import java.util.List;
import java.util.function.Supplier;

public class CraneInstructionsCreator implements Supplier<List<CraneInstruction>> {
    private final InputDataLoader dataLoader;
    public CraneInstructionsCreator(InputDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }
    @Override
    public List<CraneInstruction> get() {
        return dataLoader.loadData().stream()
                .map(CraneInstruction::of)
                .toList();
    }
}
