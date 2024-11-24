package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingBoy {

    protected final List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy() {
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots.addAll(parkingLots);
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();
        return forceParkIn(car, parkingLot);
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
        return parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .findFirst()
                .orElse(parkingLots.get(0));
    }

    public Ticket forceParkIn(Car car, ParkingLot parkingLot) {
        int parkingLotNumber = parkingLots.indexOf(parkingLot);
        if (parkingLotNumber == -1) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        ticket.setParkingLotNumber(parkingLots.indexOf(parkingLot));
        return ticket;
    }

    public void assignParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots.clear();
        this.parkingLots.addAll(parkingLots);
    }
}
