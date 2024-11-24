package com.parkinglot.strategy;

import com.parkinglot.ParkingBoy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StrategyFactory {

    private final Map<StrategyEnum, ParkingBoy> STRATEGY_MAP;

    public StrategyFactory() {
        STRATEGY_MAP = Arrays.stream(StrategyEnum.values())
                .collect(Collectors.toMap(Function.identity(), StrategyEnum::getParkingBoyImplementation));
    }

    public ParkingBoy getParkingBoy(StrategyEnum strategyEnum) {
        return STRATEGY_MAP.get(strategyEnum);
    }

}
