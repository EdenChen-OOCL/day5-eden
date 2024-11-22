package com.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_when_park_given_a_car_and_parking_lot_is_empty() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_fetch_given_a_valid_tiket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();

        Car parkCar = new Car();
        Ticket ticket = parkingLot.park(parkCar);
        // When
        Car fetchCar = parkingLot.fetch(ticket);
        // Then
        assertEquals(parkCar, fetchCar);
    }

    @Test
    public void should_return_right_car_when_fetch_given_two_car_and_two_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = parkingLot.park(firstCar);
        Ticket secondTicket = parkingLot.park(secondCar);
        // When
        Car firstFetchedCar = parkingLot.fetch(firstTicket);
        Car secondFetchedCar = parkingLot.fetch(secondTicket);
        // Then
        assertEquals(firstCar, firstFetchedCar);
        assertEquals(secondCar, secondFetchedCar);
    }
}
