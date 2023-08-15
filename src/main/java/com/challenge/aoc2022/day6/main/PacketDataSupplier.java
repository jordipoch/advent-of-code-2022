package com.challenge.aoc2022.day6.main;

import com.challenge.aoc2022.input.InputDataLoader;

import javax.inject.Inject;
import java.util.function.Supplier;

public class PacketDataSupplier implements Supplier<String> {
    private final InputDataLoader inputDataLoader;

    @Inject
    public PacketDataSupplier(InputDataLoader inputDataLoader) {
        this.inputDataLoader = inputDataLoader;
    }

    @Override
    public String get() {
        return inputDataLoader.loadData().get(0);
    }
}
