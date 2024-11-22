package com.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void should_return_null_ticket_when_park_given_parking_lot_is_full() {
        // Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car firstCar = new Car();
        Car secondCar = new Car();
        // When
        Ticket firstTicket = parkingLot.park(firstCar);
        // Then
        assertNotNull(firstTicket);
        assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(secondCar), "No available position.");
    }

    @Test
    public void should_return_null_car_and_print_error_message_when_fetch_given_wrong_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Ticket wrongTicket = new Ticket();
        // When

        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(wrongTicket), "Unrecognized parking ticket");
    }

    @Test
    public void should_return_null_car_and_print_error_message_when_fetch_given_ticket_use_twice() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        // When
        parkingLot.fetch(ticket);
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(ticket), "Unrecognized parking ticket");
    }


}
