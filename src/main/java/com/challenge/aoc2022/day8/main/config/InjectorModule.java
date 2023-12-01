package com.challenge.aoc2022.day8.main.config;

import com.challenge.aoc2022.day8.TreeHeightsSupplier;
import com.challenge.aoc2022.input.InputDataLoader;
import com.challenge.aoc2022.input.InputFileDataLoaderForProd;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

import java.util.List;
import java.util.function.Supplier;

public class InjectorModule extends AbstractModule {

    @Provides
    protected InputDataLoader provideInputDataLoader() {
        return new InputFileDataLoaderForProd(8, "input.txt");
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<Supplier<List<Integer>>>() {})
                .to(TreeHeightsSupplier.class);
    }
}
