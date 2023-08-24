package com.challenge.aoc2022.day7.command.result;

import com.challenge.aoc2022.day7.filesystem.Filesystem;
import com.challenge.aoc2022.day7.filesystem.exception.ElementAlreadyExistsException;

public interface CommandResult {
    void process(Filesystem filesystem) throws ElementAlreadyExistsException;
}
