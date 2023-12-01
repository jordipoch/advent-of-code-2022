package com.challenge.aoc2022.day8.treegrid;

import com.challenge.aoc2022.day8.treegrid.Grid2D;
import com.challenge.aoc2022.day8.treegrid.Tree;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Grid2DTest {
    @Test
    public void testGrid2DCreation() {
        var grid = Grid2D.create(Tree.class, List.of(Tree.of(3), Tree.of(5), Tree.of(0), Tree.of(9)));

        assertThat(grid.getAtPosition(0, 0).getHeight()).isEqualTo(3);
        assertThat(grid.getAtPosition(1, 0).getHeight()).isEqualTo(5);
        assertThat(grid.getAtPosition(0, 1).getHeight()).isEqualTo(0);
        assertThat(grid.getAtPosition(1, 1).getHeight()).isEqualTo(9);
    }

    @Test
    public void testToString() {
        List<Tree> elements = List.of(
                Tree.of(1), Tree.of(2), Tree.of(3),
                Tree.of(4), Tree.of(5), Tree.of(6),
                Tree.of(7), Tree.of(8), Tree.of(9)
        );
        elements.forEach(e -> e.setVisible(true));

        var grid = Grid2D.create(Tree.class, elements);
        grid.getAtPosition(1, 1).setVisible(false);


        assertThat(grid).hasToString("""
                \s1 2 3
                \s4/5 6
                \s7 8 9""");
    }
}