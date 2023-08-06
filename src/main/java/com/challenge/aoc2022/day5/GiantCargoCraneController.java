package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.day5.crane.GiantCargoCrane;
import com.challenge.aoc2022.day5.crane.GiantCargoCraneType;
import com.challenge.aoc2022.day5.exception.GiantCargoCraneControllerException;
import com.challenge.aoc2022.day5.exception.ShipSuppliesCreationException;
import com.challenge.library.files.TextFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.challenge.aoc2022.day5.crane.GiantCargoCraneType.CRATE_MOVER_9000;

public class GiantCargoCraneController {
    private static final Logger logger = LogManager.getLogger();
    private final GiantCargoCrane cargoCrane;
    private final List<CraneInstruction> instructions;

    public GiantCargoCraneController(GiantCargoCrane cargoCrane, List<CraneInstruction> instructions) {
        this.cargoCrane = cargoCrane;
        this.instructions = instructions;
    }

    public String process() {
        logger.info("Status of ship supplies before processing the instructions:\n{}", cargoCrane);
        instructions.forEach(cargoCrane::processInstruction);
        logger.info("Status of ship supplies after processing the instructions:\n{}", cargoCrane);
        return cargoCrane.getTopMostCrates();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private static final Path RESOURCE_PATH = Path.of("resources", "com", "challenge", "aoc2022", "day5");
        private static final Path TEST = Path.of("src", "test");
        private Path path = Path.of("src", "main").resolve(RESOURCE_PATH);
        private ShipSupplies shipSupplies;
        private List<CraneInstruction> instructions;

        private GiantCargoCraneType craneType = CRATE_MOVER_9000;

        public Builder forTest() {
            path = TEST.resolve(RESOURCE_PATH);
            return this;
        }

        public Builder withCraneType(GiantCargoCraneType craneType) {
            this.craneType = craneType;
            return this;
        }
        public Builder withSupplies(String filename) throws GiantCargoCraneControllerException {
            shipSupplies = createShipSupplies(filename);
            return this;
        }

        public Builder withInstructions(String filename) throws GiantCargoCraneControllerException {
            try {
                instructions = TextFileReader.readAllLinesFromFile(path.resolve(filename)).stream()
                        .map(CraneInstruction::of)
                        .toList();
            } catch (IOException e) {
                throw new GiantCargoCraneControllerException("Error reading instructions", e);
            }
            return this;
        }

        public GiantCargoCraneController build() {
            var cargoCrane = craneType.createCrane(shipSupplies);
            return new GiantCargoCraneController(cargoCrane, instructions);
        }

        private ShipSupplies createShipSupplies(String filename) throws GiantCargoCraneControllerException {
            try {
                return ShipSupplies.builder()
                        .fromFile(path.resolve(filename))
                        .build();
            } catch (ShipSuppliesCreationException e) {
                throw new GiantCargoCraneControllerException("Error loading supplies", e);
            }
        }
    }
}
