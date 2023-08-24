package com.challenge.aoc2022.day7.filesystem.exception;

public class ElementAlreadyExistsException extends FilesystemException {
    public ElementAlreadyExistsException(String message) {
        super(message);
    }
}
