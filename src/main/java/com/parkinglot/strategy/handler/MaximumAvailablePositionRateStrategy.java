package com.parkinglot.strategy.handler;

import com.parkinglot.ParkingLot;
import java.util.Comparator;
import java.util.List;

public class MaximumAvailablePositionRateStrategy extends ParkingStrategy {

    @Override
    public ParkingLot getBestParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                .orElse(parkingLots.get(0));
    }
}
