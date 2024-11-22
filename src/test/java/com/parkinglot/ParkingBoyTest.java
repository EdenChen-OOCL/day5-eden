package com.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParkingBoyTest {

    @Test
    public void should_return_ticket_when_park_given_a_car_and_parking_boy() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_fetch_given_a_valid_tiket_and_parking_boy() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();

        Car parkCar = new Car();
        Ticket ticket = parkingBoy.park(parkCar);
        // When
        Car fetchCar = parkingBoy.fetch(ticket);
        // Then
        assertEquals(parkCar, fetchCar);
    }

    @Test
    public void should_return_right_car_when_fetch_given_two_car_and_two_ticket_and_parking_boy() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = parkingBoy.park(firstCar);
        Ticket secondTicket = parkingBoy.park(secondCar);
        // When
        Car firstFetchedCar = parkingBoy.fetch(firstTicket);
        Car secondFetchedCar = parkingBoy.fetch(secondTicket);
        // Then
        assertEquals(firstCar, firstFetchedCar);
        assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    public void should_throw_unRecognizedParkingTicketException_when_fetch_given_wrong_ticket_and_parking_boy() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket wrongTicket = new Ticket();
        // When

        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(wrongTicket), "Unrecognized parking ticket");
    }

    @Test
    public void should_throw_unRecognizedParkingTicketException_when_fetch_given_ticket_use_twice_and_parking_boy() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        // When
        parkingBoy.fetch(ticket);
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket), "Unrecognized parking ticket");
    }

    @Test
    public void should_return_NoAvailablePositionException_when_park_given_parking_lot_is_full() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car secondCar = new Car();
        // When
        for(int times = 1; times <= 10; times++) {
            parkingBoy.park(new Car());
        }
        // Then
        assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(secondCar), "No available position.");
    }

}
