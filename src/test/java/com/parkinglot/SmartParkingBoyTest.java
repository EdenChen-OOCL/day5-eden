package com.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SmartParkingBoyTest {
    @Test
    public void should_park_in_first_when_park_given_two_parking_lot_has_same_empty_position() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SmartParkingBoy parkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));
        // When
        Ticket ticket = parkingBoy.park(new Car());
        ParkingLot targetParkingLot = parkingBoy.getParkingLotByTicket(ticket);
        // Then
        assertEquals(firstParkingLot, targetParkingLot);
    }

    @Test
    public void should_park_in_second_when_park_given_two_parking_lot_has_second_more_empty_position() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(10);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));
        // When
        Ticket ticket = parkingBoy.park(new Car());
        ParkingLot targetParkingLot = parkingBoy.getParkingLotByTicket(ticket);
        // Then
        assertEquals(secondParkingLot, targetParkingLot);
    }

}
