package com.challenge.aoc2022.day7.command;

import com.challenge.aoc2022.day7.command.result.CommandResult;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CommandFactory {

    public Command createMoveToRootCommand() {
        return new MoveToRootCommand();
    }

    public Command createMoveToParentCommand() {
        return new MoveToParentCommand();
    }

    public Command createMoveToSubfolderCommand(String folderName) {
        return new MoveToSubfolderCommand(folderName);
    }

    public Command createListCommand(List<CommandResult> commandResults) {
        return new ListCommand(commandResults);
    }
}
