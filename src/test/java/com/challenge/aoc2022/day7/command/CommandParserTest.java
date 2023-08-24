package com.challenge.aoc2022.day7.command;

import com.challenge.aoc2022.day7.parser.CommandParser;
import com.challenge.aoc2022.day7.parser.exception.CommandParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandParserTest {
    private CommandParser parser;

    @BeforeMethod
    public void setUp() {
        parser = new CommandParser(new CommandFactory());
    }

    @Test
    public void testParseMoveToRoot() throws CommandParseException {
        var result = parser.parse("$ cd /", emptyList());
        assertThat(result).hasToString("cd /");
    }

    @Test
    public void testParseMoveToParent() throws CommandParseException {
        var result = parser.parse("$ cd ..", emptyList());
        assertThat(result).hasToString("cd ..");
    }

    @Test
    public void testParseMoveToFolder() throws CommandParseException {
        var result = parser.parse("$ cd folder", emptyList());
        assertThat(result).hasToString("cd folder");
    }

    @Test
    public void testParseLsCommandWithNoResults() throws CommandParseException {
        var result = parser.parse("$ ls", emptyList());
        assertThat(result).hasToString("ls (results=[])");
    }

    @Test
    public void testParseLsCommandWithResults() throws CommandParseException {
        var result = parser.parse("$ ls", List.of("dir a", "124 b.txt", "12 c.txt"));
        assertThat(result).hasToString("ls (results=[dir a, 124 b.txt, 12 c.txt])");
    }

    @Test
    public void testParseUnknownCommandShouldThrowException() throws CommandParseException {
        assertThatThrownBy(() -> parser.parse("$ mv", emptyList()))
                .isInstanceOf(CommandParseException.class)
                .hasMessage("Unknown command \"$ mv\"");
    }

    @Test
    public void testParseUnknownCommandResultShouldThrowException() throws CommandParseException {
        assertThatThrownBy(() -> parser.parse("$ ls", List.of("100 a.txt", "directory dir")))
                .isInstanceOf(CommandParseException.class)
                .hasMessageStartingWith("Cannot parse");
    }
}