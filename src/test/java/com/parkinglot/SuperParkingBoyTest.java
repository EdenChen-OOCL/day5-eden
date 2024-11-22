package com.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SuperParkingBoyTest{
    @Test
    public void should_park_in_first_when_park_given_two_parking_lot_has_same_empty_position() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SuperParkingBoy parkingBoy = new SuperParkingBoy(List.of(firstParkingLot, secondParkingLot));
        // When
        Ticket ticket = parkingBoy.park(new Car());
        ParkingLot targetParkingLot = parkingBoy.getParkingLotByTicket(ticket);
        // Then
        assertEquals(firstParkingLot, targetParkingLot);
    }

    @Test
    public void should_park_in_second_when_park_given_first_has_20_available_position_rate_and_second_has_100() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(1);
        SuperParkingBoy parkingBoy = new SuperParkingBoy(List.of(firstParkingLot, secondParkingLot));
        // When
        Ticket firstTicket = parkingBoy.park(new Car());
        Ticket secondTicket = parkingBoy.park(new Car());
        ParkingLot actualFirstParkingLot = parkingBoy.getParkingLotByTicket(firstTicket);
        ParkingLot actualSecondParkingLot = parkingBoy.getParkingLotByTicket(secondTicket);
        // Then
        assertEquals(firstParkingLot, actualFirstParkingLot);
        assertEquals(secondParkingLot, actualSecondParkingLot);
    }
}
