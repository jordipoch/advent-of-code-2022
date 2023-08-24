package com.challenge.aoc2022.day7;

import com.challenge.aoc2022.day7.command.Command;
import com.challenge.aoc2022.day7.command.exception.CommandExecutionException;
import com.challenge.aoc2022.day7.exception.FolderSizesCalculationException;
import com.challenge.aoc2022.day7.filesystem.Element;
import com.challenge.aoc2022.day7.filesystem.Filesystem;
import com.challenge.aoc2022.day7.parser.exception.CommandsLoadingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.function.Supplier;

@Singleton
public class Day7Controller {
    private static final Logger logger = LogManager.getLogger();
    private final Supplier<List<Command>> commandsSupplier;
    private final Filesystem filesystem = new Filesystem();

    @Inject
    public Day7Controller(Supplier<List<Command>> commandsSupplier) {
        this.commandsSupplier = commandsSupplier;
    }

    public int sumFolderSizesLessThan(int max) throws FolderSizesCalculationException {
        executeCommands(getCommands());

        logger.printf(Level.DEBUG, "File system content:%n%s", filesystem);

        return calculateSumOfFolderSizes(max);
    }

    private List<Command> getCommands() throws FolderSizesCalculationException {
        try {
            return commandsSupplier.get();
        } catch (CommandsLoadingException e) {
            throw new FolderSizesCalculationException("Error while getting the command for input source", e);
        }
    }

    private void executeCommands(List<Command> commands) throws FolderSizesCalculationException {
        try {
            for (var command : commands)
                command.execute(filesystem);
        } catch (CommandExecutionException e) {
            throw new FolderSizesCalculationException("Error processing the commands", e);
        }
    }

    private int calculateSumOfFolderSizes(int max) {
        return filesystem.findElements(element -> element.isFolder() && element.getSize() <= max)
                .stream()
                .mapToInt(Element::getSize)
                .sum();
    }
}
