package com.challenge.aoc2022.day8;

import com.challenge.aoc2022.day8.treegrid.TreeGrid;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.function.Supplier;

@Singleton
public class Day8Controller {
    private final TreeGrid treeGrid;

    @Inject
    public Day8Controller(Supplier<List<Integer>> treeHeightsSupplier) {
        var treeHeights = treeHeightsSupplier.get();
        treeGrid = TreeGrid.create(treeHeights);
    }

    public int calculateSumOfVisibleTrees() {
        return treeGrid.calculateNumberOfVisibleTrees();
    }
}
