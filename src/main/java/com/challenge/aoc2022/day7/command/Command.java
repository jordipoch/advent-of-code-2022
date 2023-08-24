package com.challenge.aoc2022.day7.command;

import com.challenge.aoc2022.day7.command.exception.CommandExecutionException;
import com.challenge.aoc2022.day7.filesystem.Filesystem;

public interface Command {
    void execute(Filesystem filesystem) throws CommandExecutionException;
}
