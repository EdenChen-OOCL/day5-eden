package com.parkinglot.strategy.handler;

import com.parkinglot.ParkingLot;
import java.util.List;

public abstract class ParkingStrategy {

    public abstract ParkingLot getBestParkingLot(List<ParkingLot> parkingLots);
}
