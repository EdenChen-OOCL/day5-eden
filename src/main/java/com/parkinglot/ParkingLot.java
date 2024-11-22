package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {

    public static final int MAX_CAPACITY = 10;
    private int capacity;

    private final Map<Ticket, Car> ticketCarMap = new HashMap<>(MAX_CAPACITY);

    public ParkingLot() {
        this(MAX_CAPACITY);
    }

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (ticketCarMap.size() >= MAX_CAPACITY) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
//        capacity--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = ticketCarMap.remove(ticket);
        if (Objects.isNull(car)) {
            throw new UnrecognizedParkingTicketException();
        }
//        capacity++;
        return car;
    }
}
