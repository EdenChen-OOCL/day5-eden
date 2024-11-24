package com.parkinglot.strategy;

import com.parkinglot.ParkingBoy;
import com.parkinglot.SmartParkingBoy;
import com.parkinglot.SuperParkingBoy;

public enum StrategyEnum {
    SEQUENTIAL(new ParkingBoy()),
    MAXIMUM_AVAILABLE_POSITION(new SmartParkingBoy()),
    MAXIMUM_AVAILABLE_POSITION_RATE(new SuperParkingBoy()),
    ;

    private final ParkingBoy parkingBoyImplementation;

    StrategyEnum(ParkingBoy parkingBoyImplementation) {
        this.parkingBoyImplementation = parkingBoyImplementation;
    }

    public ParkingBoy getParkingBoyImplementation() {
        return parkingBoyImplementation;
    }

}
