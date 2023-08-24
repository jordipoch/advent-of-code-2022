package com.challenge.aoc2022.day7.parser;

import com.challenge.aoc2022.day7.command.Command;
import com.challenge.aoc2022.day7.parser.exception.CommandParseException;
import com.challenge.aoc2022.day7.parser.exception.CommandsLoadingException;
import com.challenge.aoc2022.input.InputDataLoader;
import com.challenge.aoc2022.input.exception.DataLoadingException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Supplier;

@Singleton
public class CommandsSupplier implements Supplier<List<Command>> {
    private final InputDataLoader dataLoader;
    private final CommandParser commandParser;

    @Inject
    public CommandsSupplier(InputDataLoader dataLoader, CommandParser commandParser) {
        this.dataLoader = dataLoader;
        this.commandParser = commandParser;
    }

    @Override
    public List<Command> get() {
        List<Command> commands = new ArrayList<>();

        var commandItems = loadData();
        var iterator = commandItems.listIterator();
        while (iterator.hasNext()) {
            var command = iterator.next();
            var resultItems = loadCommandResults(iterator);
            commands.add(parseCommand(command, resultItems));
        }

        return commands;
    }

    private List<String> loadData() {
        try {
            return dataLoader.loadData();
        } catch (DataLoadingException e) {
            throw new CommandsLoadingException("Error while loading data from source", e);
        }
    }

    private List<String> loadCommandResults(ListIterator<String> iterator) {
        List<String> resultItems = new ArrayList<>();
        boolean moreResults = true;
        while (iterator.hasNext() && moreResults) {
            var resultItem = iterator.next();
            if (resultItem.startsWith("$")) {
                moreResults = false;
                iterator.previous();
            } else {
                resultItems.add(resultItem);
            }
        }

        return resultItems;
    }

    private Command parseCommand(String command, List<String> resultItems) {
        try {
            return commandParser.parse(command, resultItems);
        } catch (CommandParseException e) {
            throw new CommandsLoadingException("Error while parsing command", e);
        }
    }
}
