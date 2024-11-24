package com.parkinglot.strategy.factory;

import com.parkinglot.strategy.handler.ParkingStrategy;
import com.parkinglot.strategy.ParkingStrategyEnum;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StrategyFactory {

    private static Map<ParkingStrategyEnum, ParkingStrategy> STRATEGY_MAP;

    private StrategyFactory() {
    }

    private static Map<ParkingStrategyEnum, ParkingStrategy> initStrategyMap() {
        return Arrays.stream(ParkingStrategyEnum.values())
                .collect(Collectors.toMap(Function.identity(), ParkingStrategyEnum::getParkingStrategy));
    }

    public static ParkingStrategy getParkingStrategy(ParkingStrategyEnum parkingStrategyEnum) {
        if (Objects.isNull(STRATEGY_MAP)) {
            STRATEGY_MAP = initStrategyMap();
        }
        return STRATEGY_MAP.get(parkingStrategyEnum);
    }

}
