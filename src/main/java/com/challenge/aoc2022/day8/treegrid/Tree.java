package com.challenge.aoc2022.day8.treegrid;

public class Tree {
    private final int height;
    private boolean visible;

    private Tree(int height) {
        this.height = height;
    }

    public static Tree of(int height) {
        return new Tree(height);
    }

    public boolean isVisible() {
        return visible;
    }

    public int getHeight() {
        return height;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return visible ? " " + height : "/" + height;
    }
}
