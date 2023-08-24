package com.challenge.aoc2022.day7.command.result;

import com.challenge.aoc2022.day7.filesystem.Filesystem;
import com.challenge.aoc2022.day7.filesystem.exception.ElementAlreadyExistsException;

public class CreateFolderResult implements CommandResult {
    private final String foldername;

    public CreateFolderResult(String foldername) {
        this.foldername = foldername;
    }

    @Override
    public void process(Filesystem filesystem) throws ElementAlreadyExistsException {
        filesystem.createFolder(foldername);
    }

    @Override
    public String toString() {
        return "dir " + foldername;
    }
}
