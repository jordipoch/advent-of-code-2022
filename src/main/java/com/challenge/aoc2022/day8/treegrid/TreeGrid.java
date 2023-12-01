package com.challenge.aoc2022.day8.treegrid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TreeGrid {
    private static final Logger logger = LogManager.getLogger();
    private final Grid2D<Tree> trees;

    private TreeGrid(Grid2D<Tree> trees) {
        this.trees = trees;
    }

    public static TreeGrid create(List<Integer> treeHeights) {
        List<Tree> trees = treeHeights.stream()
                .map(Tree::of)
                .toList();

        var grid = Grid2D.create(Tree.class, trees);
        return new TreeGrid(grid);
    }

    public int calculateNumberOfVisibleTrees() {
        updateVisibilityFromLeftSide();
        updateVisibilityFromRightSide();
        updateVisibilityFromTopSide();
        updateVisibilityFromBottomSide();

        logger.debug("Tree grid after visibility update:{}{}", System.lineSeparator(), this);

        return countVisibleTrees();
    }

    private int countVisibleTrees() {
        int count = 0;
        for (int y = 0; y < trees.getSideSize(); y ++)
            for (int x = 0; x < trees.getSideSize(); x ++)
                if (trees.getAtPosition(x, y).isVisible())
                    count ++;
        return count;
    }

    public void updateVisibilityFromLeftSide() {
        for (int i = 0; i < trees.getSideSize(); i ++)
            updateRowVisibilityFromLeftSide(i);
    }

    public void updateVisibilityFromRightSide() {
        for (int i = 0; i < trees.getSideSize(); i ++)
            updateRowVisibilityFromRightSide(i);
    }

    public void updateVisibilityFromTopSide() {
        for (int i = 0; i < trees.getSideSize(); i ++)
            updateRowVisibilityFromTopSide(i);
    }

    public void updateVisibilityFromBottomSide() {
        for (int i = 0; i < trees.getSideSize(); i ++)
            updateRowVisibilityFromBottomSide(i);
    }

    private void updateRowVisibilityFromLeftSide(int row) {
        int minHeight = -1;
        for (int i = 0; i < trees.getSideSize(); i ++) {
            Tree currentTree = trees.getAtPosition(i, row);
            if (currentTree.getHeight() > minHeight) {
                currentTree.setVisible(true);
                minHeight = currentTree.getHeight();
            }
        }
    }

    private void updateRowVisibilityFromRightSide(int row) {
        int minHeight = -1;
        for (int i = trees.getSideSize() - 1; i >= 0; i --) {
            Tree currentTree = trees.getAtPosition(i, row);
            if (currentTree.getHeight() > minHeight) {
                currentTree.setVisible(true);
                minHeight = currentTree.getHeight();
            }
        }
    }

    private void updateRowVisibilityFromTopSide(int column) {
        int minHeight = -1;
        for (int i = 0; i < trees.getSideSize(); i ++) {
            Tree currentTree = trees.getAtPosition(column, i);
            if (currentTree.getHeight() > minHeight) {
                currentTree.setVisible(true);
                minHeight = currentTree.getHeight();
            }
        }
    }

    private void updateRowVisibilityFromBottomSide(int column) {
        int minHeight = -1;
        for (int i = trees.getSideSize() - 1; i >= 0; i --) {
            Tree currentTree = trees.getAtPosition(column, i);
            if (currentTree.getHeight() > minHeight) {
                currentTree.setVisible(true);
                minHeight = currentTree.getHeight();
            }
        }
    }

    @Override
    public String toString() {
        return trees.toString();
    }
}
