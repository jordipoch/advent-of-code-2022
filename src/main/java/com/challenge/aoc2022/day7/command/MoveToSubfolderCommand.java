package com.challenge.aoc2022.day7.command;

import com.challenge.aoc2022.day7.command.exception.CommandExecutionException;
import com.challenge.aoc2022.day7.filesystem.Filesystem;
import com.challenge.aoc2022.day7.filesystem.exception.FolderNotFoundException;

public class MoveToSubfolderCommand implements Command {
    private final String folderName;
    public MoveToSubfolderCommand(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public void execute(Filesystem filesystem) throws CommandExecutionException {
        try {
            filesystem.moveToSubFolder(folderName);
        } catch (FolderNotFoundException e) {
            throw new CommandExecutionException(String.format("Error executing command \"%s\"", this), e);
        }
    }

    @Override
    public String toString() {
        return "cd " + folderName;
    }
}
