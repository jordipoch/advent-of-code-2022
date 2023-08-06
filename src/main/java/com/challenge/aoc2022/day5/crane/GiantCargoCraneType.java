package com.challenge.aoc2022.day5.crane;

import com.challenge.aoc2022.day5.ShipSupplies;

public enum GiantCargoCraneType {
    CRATE_MOVER_9000 {
        @Override
        public GiantCargoCrane createCrane(ShipSupplies shipSupplies) {
            return GiantCargoCraneFactory.createCrateMover9000(shipSupplies);
        }
    }, CRATE_MOVER_9001 {
        @Override
        public GiantCargoCrane createCrane(ShipSupplies shipSupplies) {
            return GiantCargoCraneFactory.createCrateMover9001(shipSupplies);
        }
    };

    public abstract GiantCargoCrane createCrane(ShipSupplies shipSupplies);
}
