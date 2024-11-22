package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private int capacity;

    private final Map<Ticket, Car> ticketCarMap = new HashMap<Ticket, Car>(10);

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (ticketCarMap.size() >= capacity) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        capacity--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = ticketCarMap.remove(ticket);
        if (Objects.nonNull(car)) {
            capacity++;
        }
        return car;
    }
}
