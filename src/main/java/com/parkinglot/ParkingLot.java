package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {

    private int MAX_CAPACITY = 10;

    private final Map<Ticket, Car> ticketCarMap = new HashMap<>();

    public ParkingLot() {
    }

    public ParkingLot(int maxCapacity) {
        this.MAX_CAPACITY = maxCapacity;
    }

    public Ticket park(Car car) {
        if (!isAvailable()) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public boolean isAvailable() {
        return ticketCarMap.size() < MAX_CAPACITY;
    }

    public Car fetch(Ticket ticket) {
        Car car = ticketCarMap.remove(ticket);
        if (Objects.isNull(car)) {
            throw new UnrecognizedParkingTicketException();
        }
        return car;
    }

    public int getFreeCapacity() {
        return MAX_CAPACITY - ticketCarMap.size();
    }

}
