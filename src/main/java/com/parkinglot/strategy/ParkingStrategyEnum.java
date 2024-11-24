package com.parkinglot.strategy;


import com.parkinglot.strategy.handler.MaximumAvailablePositionRateStrategy;
import com.parkinglot.strategy.handler.MaximumAvailablePositionStrategy;
import com.parkinglot.strategy.handler.ParkingStrategy;
import com.parkinglot.strategy.handler.SequenceStrategy;

public enum ParkingStrategyEnum {
    SEQUENTIAL(new SequenceStrategy()),
    MAXIMUM_AVAILABLE_POSITION(new MaximumAvailablePositionStrategy()),
    MAXIMUM_AVAILABLE_POSITION_RATE(new MaximumAvailablePositionRateStrategy()),
    ;

    private final ParkingStrategy parkingStrategy;

    ParkingStrategyEnum(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public ParkingStrategy getParkingStrategy() {
        return parkingStrategy;
    }

}
