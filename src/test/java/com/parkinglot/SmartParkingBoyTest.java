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
    public void should_park_in_second_when_park_given_first_has_1_empty_position_and_second_has_10() {
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

    @Test
    public void should_get_carA_and_carB_when_fetch_given_ticketA_and_ticketB() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SmartParkingBoy parkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car carA = new Car();
        Car carB = new Car();
        Ticket ticketA = parkingBoy.park(carA);
        Ticket ticketB = parkingBoy.park(carB);
        // When
        Car actualCarA = parkingBoy.fetch(ticketA);
        Car actualCarB = parkingBoy.fetch(ticketB);
        // Then
        assertEquals(carA, actualCarA);
        assertEquals(carB, actualCarB);
    }

}
