package com.challenge.aoc2022.day7;

import com.challenge.aoc2022.day7.command.Command;
import com.challenge.aoc2022.day7.command.exception.CommandExecutionException;
import com.challenge.aoc2022.day7.exception.FolderSizesCalculationException;
import com.challenge.aoc2022.day7.filesystem.Directory;
import com.challenge.aoc2022.day7.filesystem.Element;
import com.challenge.aoc2022.day7.filesystem.Filesystem;
import com.challenge.aoc2022.day7.parser.exception.CommandsLoadingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Singleton
public class Day7Controller {
    private static final Logger logger = LogManager.getLogger();
    private final Supplier<List<Command>> commandsSupplier;
    private final Filesystem filesystem = new Filesystem();

    @Inject
    public Day7Controller(Supplier<List<Command>> commandsSupplier) {
        this.commandsSupplier = commandsSupplier;
    }

    public int calculateSumOfDirectorySizesLessThan(int max) throws FolderSizesCalculationException {
        executeCommands(getCommands());
        logger.printf(Level.DEBUG, "File system content:%n%s", filesystem);

        return sumDirectorySizes(max);
    }

    public int findSizeOfDirectoryToRemove(int fileSystemTotalSize, int minSpaceNeeded) throws FolderSizesCalculationException, FolderToRemoveNotFoundException {
        executeCommands(getCommands());
        logger.printf(Level.DEBUG, "File system content:%n%s", filesystem);

        var dir = findDirectoryToRemove(fileSystemTotalSize, minSpaceNeeded);
        logger.debug("Directory found to be removed: {}", dir.getName());

        return dir.getSize();
    }

    private Directory findDirectoryToRemove(int fileSystemTotalSize, int minSpaceNeeded) throws FolderToRemoveNotFoundException {
        int minSpaceToFreeUp = getMinSpaceToFreeUp(fileSystemTotalSize, minSpaceNeeded);
        var candidates = findCandidatesForRemoval(minSpaceToFreeUp);
        return selectFolderWithMinSize(candidates);
    }

    private int getMinSpaceToFreeUp(int fileSystemTotalSize, int minSpaceNeeded) {
        int spaceCurrentlyInUse = filesystem.getSpaceUsed();
        int spaceCurrentlyFree = fileSystemTotalSize - spaceCurrentlyInUse;
        int minSpaceToFreeUp = minSpaceNeeded - spaceCurrentlyFree;
        logger.debug("Space currently in use: {}, Current space left: {}, min space to free up: {}", spaceCurrentlyInUse, spaceCurrentlyFree, minSpaceToFreeUp);
        return minSpaceToFreeUp;
    }

    private Set<Directory> findCandidatesForRemoval(int minSpaceToFreeUp) {
        var candidates = filesystem.findElements(element -> element.isFolder() && element.getSize() >= minSpaceToFreeUp).stream()
                .map(Directory.class::cast)
                .collect(Collectors.toSet());

        logger.debug("Directories candidates for removal {}",
                candidates.stream()
                        .map(d -> String.format("%s (size=%d)", d.getName(), d.getSize()))
                        .collect(Collectors.toSet()));

        return candidates;
    }

    private static Directory selectFolderWithMinSize(Set<Directory> candidates) throws FolderToRemoveNotFoundException {
        return candidates.stream()
                .min(Comparator.comparing(Element::getSize))
                .orElseThrow(() -> new FolderToRemoveNotFoundException("Cannot find a folder with the min required size"));
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

    private int sumDirectorySizes(int max) {
        return filesystem.findElements(element -> element.isFolder() && element.getSize() <= max)
                .stream()
                .mapToInt(Element::getSize)
                .sum();
    }
}
