package com.challenge.aoc2022.day5.crane;

import com.challenge.aoc2022.day5.CraneInstruction;

public interface GiantCargoCrane {
    void processInstruction(CraneInstruction instruction);

    String getTopMostCrates();
}
