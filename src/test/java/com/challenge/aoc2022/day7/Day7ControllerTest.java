package com.challenge.aoc2022.day7;

import com.challenge.aoc2022.day7.command.CommandFactory;
import com.challenge.aoc2022.day7.exception.FolderSizesCalculationException;
import com.challenge.aoc2022.day7.parser.CommandParser;
import com.challenge.aoc2022.day7.parser.CommandsSupplier;
import com.challenge.aoc2022.input.InputFileDataLoaderForTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day7ControllerTest {

    @Test
    public void sumFolderSizesLessThan() throws FolderSizesCalculationException {
        var commandSupplier = new CommandsSupplier(
                new InputFileDataLoaderForTest(7, "example_input.txt"),
                new CommandParser(new CommandFactory())
        );

        var controller = new Day7Controller(commandSupplier);

        assertThat(controller.sumFolderSizesLessThan(100_000)).isEqualTo(95_437);
    }
}