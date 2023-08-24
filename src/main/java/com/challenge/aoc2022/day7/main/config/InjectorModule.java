package com.challenge.aoc2022.day7.main.config;

import com.challenge.aoc2022.day7.command.Command;
import com.challenge.aoc2022.day7.parser.CommandsSupplier;
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
        return new InputFileDataLoaderForProd(7, "input.txt");
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<Supplier<List<Command>>>() {})
                .to(CommandsSupplier.class);
    }
}
