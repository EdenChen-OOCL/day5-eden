package com.ParkingBoy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.parkinglot.Car;
import com.parkinglot.ParkingBoy;
import com.parkinglot.Ticket;
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

}
