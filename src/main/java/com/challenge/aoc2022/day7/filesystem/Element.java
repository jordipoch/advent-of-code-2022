package com.challenge.aoc2022.day7.filesystem;

public abstract class Element {
    protected final String name;
    protected int size;

    public Element(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }

    public abstract boolean isFile();

    public abstract boolean isFolder();
}
