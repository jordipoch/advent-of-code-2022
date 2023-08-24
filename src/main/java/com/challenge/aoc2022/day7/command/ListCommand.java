package com.challenge.aoc2022.day7.command;

import com.challenge.aoc2022.day7.command.exception.CommandExecutionException;
import com.challenge.aoc2022.day7.command.result.CommandResult;
import com.challenge.aoc2022.day7.filesystem.Filesystem;
import com.challenge.aoc2022.day7.filesystem.exception.ElementAlreadyExistsException;

import java.util.List;

public class ListCommand implements Command {
    private final List<CommandResult> commandResults;
    public ListCommand(List<CommandResult> commandResults) {
        this.commandResults = commandResults;
    }

    @Override
    public void execute(Filesystem filesystem) throws CommandExecutionException {
        try {
            for (var result : commandResults) {
                result.process(filesystem);
            }
        } catch (ElementAlreadyExistsException e) {
            throw new CommandExecutionException(String.format("Error while processing results from command \"%s\"", this), e);
        }
    }

    @Override
    public String toString() {
        return String.format("ls (results=%s)", commandResults);
    }
}
