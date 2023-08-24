package com.challenge.aoc2022.day7.parser.exception;

public class CommandsLoadingException extends RuntimeException {
    public CommandsLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
