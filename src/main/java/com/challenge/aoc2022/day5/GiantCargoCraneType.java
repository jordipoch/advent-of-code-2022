package com.challenge.aoc2022.day5;

public enum GiantCargoCraneType {
    CRATE_MOVER_9000 {
        @Override
        public GiantCargoCrane createCrane(ShipSupplies shipSupplies) {
            return GiantCargoCraneFactory.createCrateMover9000(shipSupplies);
        }
    }, CRATE_MOVER_9001 {
        @Override
        public GiantCargoCrane createCrane(ShipSupplies shipSupplies) {
            return null;
        }
    };

    public abstract GiantCargoCrane createCrane(ShipSupplies shipSupplies);
}
