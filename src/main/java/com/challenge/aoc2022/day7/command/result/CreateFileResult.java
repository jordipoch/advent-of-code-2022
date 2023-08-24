package com.challenge.aoc2022.day7.command.result;

import com.challenge.aoc2022.day7.filesystem.Filesystem;
import com.challenge.aoc2022.day7.filesystem.exception.ElementAlreadyExistsException;

public class CreateFileResult implements CommandResult {
    private final String filename;
    private final int size;

    public CreateFileResult(String filename, int size) {
        this.filename = filename;
        this.size = size;
    }

    @Override
    public void process(Filesystem filesystem) throws ElementAlreadyExistsException {
        filesystem.createFile(filename, size);
    }

    @Override
    public String toString() {
        return String.format("%d %s", size, filename);
    }
}
