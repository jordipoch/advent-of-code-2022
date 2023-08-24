package com.challenge.aoc2022.day7.command.exception;

public class CommandExecutionException extends Exception {
    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
