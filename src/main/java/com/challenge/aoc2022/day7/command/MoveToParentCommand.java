package com.challenge.aoc2022.day7.command;

import com.challenge.aoc2022.day7.filesystem.Filesystem;

public class MoveToParentCommand implements Command {
    @Override
    public void execute(Filesystem filesystem) {
        filesystem.moveToParentFolder();
    }

    @Override
    public String toString() {
        return "cd ..";
    }
}
