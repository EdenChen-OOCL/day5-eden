package com.parkinglot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {

    private int maxCapacity = 10;

    private final Map<Ticket, Car> ticketCarMap = new HashMap<>();

    public ParkingLot() {
    }

    public ParkingLot(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Ticket park(Car car) {
        if (!isAvailable()) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = ticketCarMap.remove(ticket);
        if (Objects.isNull(car)) {
            throw new UnrecognizedParkingTicketException();
        }
        return car;
    }

    public boolean isAvailable() {
        return ticketCarMap.size() < maxCapacity;
    }

    public int getAvailableCapacity() {
        return maxCapacity - ticketCarMap.size();
    }

}
