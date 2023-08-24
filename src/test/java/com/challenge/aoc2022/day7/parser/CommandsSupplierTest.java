package com.challenge.aoc2022.day7.parser;

import com.challenge.aoc2022.day7.command.CommandFactory;
import com.challenge.aoc2022.input.InputDataLoader;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

public class CommandsSupplierTest {

    @Test
    public void testGet() {
        InputDataLoader dataLoader = () -> List.of(
                "$ cd /",
                "$ ls",
                "dir a",
                "14848514 b.txt",
                "8504156 c.dat",
                "dir d",
                "$ cd a",
                "$ ls",
                "dir e",
                "29116 f",
                "2557 g",
                "62596 h.lst",
                "$ cd e",
                "$ ls",
                "584 i",
                "$ cd ..",
                "$ cd ..",
                "$ cd d",
                "$ ls",
                "4060174 j",
                "8033020 d.log",
                "5626152 d.ext",
                "7214296 k"
        );

        var commandSupplier = new CommandsSupplier(dataLoader, new CommandParser(new CommandFactory()));
        var commands = commandSupplier.get();

        Assertions.assertThat(commands).hasToString("[" +
                "cd /, " +
                "ls (results=[dir a, 14848514 b.txt, 8504156 c.dat, dir d]), " +
                "cd a, " +
                "ls (results=[dir e, 29116 f, 2557 g, 62596 h.lst]), " +
                "cd e, " +
                "ls (results=[584 i]), " +
                "cd .., " +
                "cd .., " +
                "cd d, " +
                "ls (results=[4060174 j, 8033020 d.log, 5626152 d.ext, 7214296 k])" +
                "]"
        );
    }
}