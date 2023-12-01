package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.day5.crane.GiantCargoCrane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GiantCargoCraneController {
    private static final Logger logger = LogManager.getLogger();
    private final GiantCargoCrane cargoCrane;
    private final List<CraneInstruction> instructions;

    public static GiantCargoCraneController of(GiantCargoCrane cargoCrane, List<CraneInstruction> instructions) {
        return new GiantCargoCraneController(cargoCrane, instructions);
    }
    public GiantCargoCraneController(GiantCargoCrane cargoCrane, List<CraneInstruction> instructions) {
        this.cargoCrane = cargoCrane;
        this.instructions = instructions;
    }

    public String process() {
        logger.debug("Status of ship supplies before processing the instructions:\n{}", cargoCrane);
        instructions.forEach(cargoCrane::processInstruction);
        logger.debug("Status of ship supplies after processing the instructions:\n{}", cargoCrane);
        return cargoCrane.getTopMostCrates();
    }
}
