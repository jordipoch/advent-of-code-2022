package com.challenge.aoc2022.day5.main.config;

import com.challenge.aoc2022.day5.CraneInstruction;
import com.challenge.aoc2022.day5.ShipSupplies;
import com.challenge.aoc2022.day5.main.CraneInstructionsSupplier;
import com.challenge.aoc2022.day5.main.ShipSuppliesSupplier;
import com.challenge.aoc2022.input.InputDataLoader;
import com.challenge.aoc2022.input.InputFileDataLoaderForProd;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

import java.util.List;
import java.util.function.Supplier;

public class InjectorModule extends AbstractModule {

    @Supplies
    @Provides
    protected InputDataLoader provideShipSuppliesDataLoader() {
        return new InputFileDataLoaderForProd(5, "ship_supplies.txt");
    }

    @Instructions
    @Provides
    protected InputDataLoader provideCraneInstructionsDataLoader() {
        return new InputFileDataLoaderForProd(5, "instructions.txt");
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<Supplier<ShipSupplies>>() {})
                .to(ShipSuppliesSupplier.class);

        bind(new TypeLiteral<Supplier<List<CraneInstruction>>>() {})
                .to(CraneInstructionsSupplier.class);
    }
}
