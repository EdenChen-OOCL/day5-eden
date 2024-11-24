package com.parkinglot.strategy.handler;

import com.parkinglot.ParkingLot;
import java.util.List;

public class SequenceStrategy extends ParkingStrategy {

    @Override
    public ParkingLot getBestParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .findFirst()
                .orElse(parkingLots.get(0));
    }
}
