package com.challenge.aoc2022.day7;

import com.challenge.aoc2022.day7.command.CommandFactory;
import com.challenge.aoc2022.day7.exception.FolderSizesCalculationException;
import com.challenge.aoc2022.day7.parser.CommandParser;
import com.challenge.aoc2022.day7.parser.CommandsSupplier;
import com.challenge.aoc2022.input.InputFileDataLoaderForTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day7ControllerTest {
    private Day7Controller controller;
    @BeforeMethod
    public void setUp() {
        var commandsSupplier = new CommandsSupplier(
                new InputFileDataLoaderForTest(7, "example_input.txt"),
                new CommandParser(new CommandFactory())
        );
        controller = new Day7Controller(commandsSupplier);
    }

    @Test
    public void testCalculateSumOfDirectorySizesLessThan() throws FolderSizesCalculationException {
        assertThat(controller.calculateSumOfDirectorySizesLessThan(100_000)).isEqualTo(95_437);
    }
    
    @Test
    public void testFindSizeOfFolderToRemove() throws FolderSizesCalculationException, FolderToRemoveNotFoundException {
        assertThat(controller.findSizeOfDirectoryToRemove(70_000_000, 30_000_000)).isEqualTo(24_933_642);
    } 
}