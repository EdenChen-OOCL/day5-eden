package com.parkinglot.strategy;

import com.parkinglot.ParkingBoy;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StrategyFactory {

    private static Map<StrategyEnum, ParkingBoy> STRATEGY_MAP;

    private StrategyFactory() {
    }

    private static Map<StrategyEnum, ParkingBoy> initStrategyMap() {
        return Arrays.stream(StrategyEnum.values())
                .collect(Collectors.toMap(Function.identity(), StrategyEnum::getParkingBoyImplementation));
    }

    public static ParkingBoy getParkingBoy(StrategyEnum strategyEnum) {
        if (Objects.isNull(STRATEGY_MAP)) {
            STRATEGY_MAP = initStrategyMap();
        }
        return STRATEGY_MAP.get(strategyEnum);
    }

}
