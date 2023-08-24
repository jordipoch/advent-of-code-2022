package com.challenge.aoc2022.day7.filesystem;

public class File extends Element {

    public static File of(String name, int size) {
        return new File(name, size);
    }
    private File(String name, int size) {
        super(name, size);
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isFolder() {
        return false;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format("- %s (file, size=%d)", name, size);
    }
}
