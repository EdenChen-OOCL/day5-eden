package com.parkinglot;

import com.parkinglot.strategy.ParkingStrategyEnum;
import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy() {
        super(ParkingStrategyEnum.MAXIMUM_AVAILABLE_POSITION_RATE);
    }

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots, ParkingStrategyEnum.MAXIMUM_AVAILABLE_POSITION_RATE);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                .orElse(parkingLots.get(0));
    }
}
