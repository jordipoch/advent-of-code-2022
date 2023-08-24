package com.challenge.aoc2022.day7.command;

import com.challenge.aoc2022.day7.filesystem.Filesystem;

public class MoveToRootCommand implements Command {
    @Override
    public void execute(Filesystem filesystem) {
        filesystem.moveRootFolder();
    }

    @Override
    public String toString() {
       return "cd /";
    }
}
