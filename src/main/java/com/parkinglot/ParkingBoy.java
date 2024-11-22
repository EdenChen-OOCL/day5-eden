package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private final List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy() {
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots.addAll(parkingLots);
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();
        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        ParkingLot parkingLot = getParkingLotByTicket(ticket);
        return parkingLot.fetch(ticket);
    }

    public ParkingLot getParkingLotByTicket(Ticket ticket) {
        int parkingLotNumber = ticket.getParkingLotNumber();
        return parkingLots.get(parkingLotNumber);
    }

    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream().filter(ParkingLot::isAvailable).findFirst().orElse(parkingLots.get(0));
    }
}
