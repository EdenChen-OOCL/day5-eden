package com.parkinglot;

import com.parkinglot.strategy.ParkingStrategyEnum;
import com.parkinglot.strategy.factory.StrategyFactory;
import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    protected final List<ParkingLot> parkingLots = new ArrayList<>();

    private ParkingStrategyEnum preferParkingStrategy;

    public ParkingBoy() {
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        preferParkingStrategy = ParkingStrategyEnum.SEQUENTIAL;
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots.addAll(parkingLots);
        preferParkingStrategy = ParkingStrategyEnum.SEQUENTIAL;
    }

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingStrategyEnum preferParkingStrategy) {
        this(parkingLots);
        this.preferParkingStrategy = preferParkingStrategy;
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = StrategyFactory.getParkingStrategy(preferParkingStrategy).getBestParkingLot(parkingLots);
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

    public Ticket forceParkIn(Car car, ParkingLot parkingLot) {
        int parkingLotNumber = parkingLots.indexOf(parkingLot);
        if (parkingLotNumber == -1) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        ticket.setParkingLotNumber(parkingLots.indexOf(parkingLot));
        return ticket;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots.clear();
        this.parkingLots.addAll(parkingLots);
    }

    public void setPreferParkingStrategy(ParkingStrategyEnum preferParkingStrategy) {
        this.preferParkingStrategy = preferParkingStrategy;
    }
}
