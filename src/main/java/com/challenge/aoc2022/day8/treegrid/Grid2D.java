package com.challenge.aoc2022.day8.treegrid;

import java.lang.reflect.Array;
import java.util.List;

import static java.lang.Math.sqrt;

public class Grid2D<E> {
    private final E[][] elements;
    private final int sideSize;

    @SuppressWarnings("unchecked")
    public Grid2D(Class<E> clazz, int sideSize) {
        elements = (E[][]) Array.newInstance(clazz, sideSize, sideSize);
        this.sideSize = sideSize;
    }

    public E getAtPosition(int x, int y) {
        return elements[x][y];
    }

    public void setAtPosition(E element, int x, int y) {
        elements[x][y] = element;
    }

    public int getSideSize() {
        return sideSize;
    }



    @Override
    public String toString() {
        var result = new StringBuilder();
        for (int y = 0; y < sideSize; y ++) {
            for (int x = 0; x < sideSize; x++)
                result.append(elements[x][y]);
            result.append("\n");
        }

        return result.substring(0, result.length()-1);
    }

    public static <E> Grid2D<E> create(Class<E> clazz, List<E> elements) {
        int size = (int) sqrt(elements.size());
        var grid = new Grid2D<>(clazz, size);
        fillGrid(grid, elements);
        return grid;
    }

    private static <E> void fillGrid(Grid2D<E> grid, List<E> elements) {
        int i = 0;
        for (int y = 0; y < grid.getSideSize(); y ++)
            for (int x = 0; x < grid.getSideSize(); x ++)
                grid.setAtPosition(elements.get(i++), x, y);
    }
}
