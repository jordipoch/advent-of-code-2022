package com.challenge.aoc2022.day7.filesystem.exception;

public class FolderNotFoundException extends FilesystemException {
    public FolderNotFoundException(String message) {
        super(message);
    }
}
