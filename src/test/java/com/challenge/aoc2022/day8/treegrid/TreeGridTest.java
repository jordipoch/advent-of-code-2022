package com.challenge.aoc2022.day8.treegrid;

import com.challenge.aoc2022.day8.treegrid.TreeGrid;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class TreeGridTest {
    private TreeGrid treeGrid;
    @BeforeMethod
    public void setUp() {
        treeGrid = TreeGrid.create(List.of(
                3, 0, 3, 7, 3,
                2, 5, 5, 1, 2,
                6, 5, 3, 3, 2,
                3, 3, 5, 4, 9,
                3, 5, 3, 9, 0
        ));
    }

    @Test
    public void testUpdateVisibilityFromLeftSide() {
        treeGrid.updateVisibilityFromLeftSide();
        assertThat(treeGrid).hasToString("""
                \s3/0/3 7/3
                \s2 5/5/1/2
                \s6/5/3/3/2
                \s3/3 5/4 9
                \s3 5/3 9/0""");
    }

    @Test
    public void testUpdateVisibilityFromRightSide() {
        treeGrid.updateVisibilityFromRightSide();
        assertThat(treeGrid).hasToString("""
                /3/0/3 7 3
                /2/5 5/1 2
                \s6 5/3 3 2
                /3/3/5/4 9
                /3/5/3 9 0""");
    }

    @Test
    public void testUpdateVisibilityFromTopSide() {
        treeGrid.updateVisibilityFromTopSide();
        assertThat(treeGrid).hasToString("""
                \s3 0 3 7 3
                /2 5 5/1/2
                \s6/5/3/3/2
                /3/3/5/4 9
                /3/5/3 9/0""");
    }

    @Test
    public void testUpdateVisibilityFromBottomSide() {
        treeGrid.updateVisibilityFromBottomSide();
        assertThat(treeGrid).hasToString("""
                /3/0/3/7/3
                /2/5/5/1/2
                \s6/5/3/3/2
                /3/3 5/4 9
                \s3 5 3 9 0""");
    }

    @Test
    public void testCountVisibleTrees() {
        assertThat(treeGrid.calculateNumberOfVisibleTrees()).isEqualTo(21);
    }
}