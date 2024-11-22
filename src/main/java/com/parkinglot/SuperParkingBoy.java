package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                .orElse(parkingLots.get(0));
    }
}
