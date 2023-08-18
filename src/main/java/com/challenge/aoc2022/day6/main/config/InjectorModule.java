package com.challenge.aoc2022.day6.main.config;

import com.challenge.aoc2022.day6.factory.MarkerPositionCalculatorFactory;
import com.challenge.aoc2022.day6.factory.MarkerPositionCalculatorUsingStreamsFactory;
import com.challenge.aoc2022.day6.main.PacketDataSupplier;
import com.challenge.aoc2022.input.InputDataLoader;
import com.challenge.aoc2022.input.InputFileDataLoaderForProd;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

import java.util.function.Supplier;

public class InjectorModule extends AbstractModule {

    @Provides
    protected InputDataLoader providePacketDataLoader() {
        return new InputFileDataLoaderForProd(6, "input.txt");
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<Supplier<String>>() {})
                .to(PacketDataSupplier.class);

        bind(MarkerPositionCalculatorFactory.class).to(MarkerPositionCalculatorUsingStreamsFactory.class);
    }
}
