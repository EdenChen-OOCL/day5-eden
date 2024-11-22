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
}
