package com.parkinglot;

import com.parkinglot.strategy.ParkingStrategyEnum;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy() {
        super(ParkingStrategyEnum.MAXIMUM_AVAILABLE_POSITION);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots, ParkingStrategyEnum.MAXIMUM_AVAILABLE_POSITION);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .max(Comparator.comparingInt(ParkingLot::getAvailableCapacity))
                .orElse(parkingLots.get(0));
    }
}
