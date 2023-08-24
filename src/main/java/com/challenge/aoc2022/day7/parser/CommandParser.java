package com.challenge.aoc2022.day7.parser;

import com.challenge.aoc2022.day7.command.Command;
import com.challenge.aoc2022.day7.command.CommandFactory;
import com.challenge.aoc2022.day7.command.result.CommandResult;
import com.challenge.aoc2022.day7.command.result.CreateFileResult;
import com.challenge.aoc2022.day7.command.result.CreateFolderResult;
import com.challenge.aoc2022.day7.parser.exception.CommandParseException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Singleton
public class CommandParser {
    public static final Pattern FILE_PATTERN = Pattern.compile("^(\\d+) (\\p{Alnum}+(\\.\\p{Alnum}+)?)$");
    public static final Pattern DIR_PATTERN = Pattern.compile("^dir (\\p{Alnum}+(\\.\\p{Alnum}+)?)$");
    private final CommandFactory commandFactory;

    @Inject
    public CommandParser(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public Command parse(String command, List<String> resultItems) throws CommandParseException {
        if (isCdCommand(command)) {
            var param = command.split(" ")[2];
            return switch (param) {
                case "/" -> commandFactory.createMoveToRootCommand();
                case ".." -> commandFactory.createMoveToParentCommand();
                default -> commandFactory.createMoveToSubfolderCommand(param);
            };
        } else if (isLsCommand(command)) {
            List<CommandResult> commandResults = parseCommandResult(resultItems);
            return commandFactory.createListCommand(commandResults);
        } else
            throw new CommandParseException(String.format("Unknown command \"%s\"", command));

    }

    private boolean isCdCommand(String command) {
        return command.startsWith("$ cd");
    }

    private boolean isLsCommand(String command) {
        return command.startsWith("$ ls");
    }

    private List<CommandResult> parseCommandResult(List<String> resultItems) throws CommandParseException {
        List<CommandResult> commandResults = new ArrayList<>();
        for (var resultItem : resultItems) {
            if (resultItem.startsWith("dir"))
                commandResults.add(createFolderResult(resultItem));
            else
                commandResults.add(createFileResult(resultItem));
        }
        return commandResults;
    }

    private CommandResult createFileResult(String resultItem) throws CommandParseException {
        var matcher = FILE_PATTERN.matcher(resultItem);
        if (matcher.find()) {
            var size = Integer.parseInt(matcher.group(1));
            var fileName = matcher.group(2);
            return new CreateFileResult(fileName, size);
        } else
            throw new CommandParseException(String.format("Cannot parse file result item: \"%s\"", resultItem));
    }

    private CommandResult createFolderResult(String resultItem) throws CommandParseException {
        var matcher = DIR_PATTERN.matcher(resultItem);
        if (matcher.find()) {
            var folderName = matcher.group(1);
            return new CreateFolderResult(folderName);
        } else
            throw new CommandParseException(String.format("Cannot parse folder result item: \"%s\"", resultItem));
    }
}
