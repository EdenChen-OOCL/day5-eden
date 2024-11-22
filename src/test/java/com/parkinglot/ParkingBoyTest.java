package com.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
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
        parkTenCar(parkingBoy);
        parkTenCar(parkingBoy);
        // Then
        assertThrows(
                NoAvailablePositionException.class,
                () -> parkingBoy.park(secondCar),
                "No available position.");
    }

    private static void parkTenCar(ParkingBoy parkingBoy) {
        for(int times = 1; times <= 10; times++) {
            parkingBoy.park(new Car());
        }
    }

    @Test
    public void should_in_first_parking_lot_when_park_given_parking_boy_has_multiple_empty_parking_lot() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        ParkingLot targetParkingLot = parkingBoy.getParkingLotByTicket(ticket);
        // Then
        assertEquals(firstParkingLot, targetParkingLot);
        assertNotEquals(secondParkingLot, targetParkingLot);
    }

    @Test
    public void should_in_second_parking_lot_when_park_given_first_is_full_and_second_is_empty() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(firstParkingLot, secondParkingLot));
        parkTenCar(parkingBoy);
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        ParkingLot targetParkingLot = parkingBoy.getParkingLotByTicket(ticket);
        // Then
        assertNotEquals(firstParkingLot, targetParkingLot);
        assertEquals(secondParkingLot, targetParkingLot);
    }

    @Test
    public void should_carA_in_parkingLotA_and_carB_in_parkingLostB_when_park_given_two_parking_lot_and_two_car() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car firstCar = new Car();
        Car secondCar = new Car();
        // When
        Ticket firstTicket = parkingBoy.forceParkIn(firstCar, firstParkingLot);
        Ticket secondTicket = parkingBoy.forceParkIn(secondCar, secondParkingLot);
        // Then
        assertEquals(firstCar, parkingBoy.fetch(firstTicket));
        assertEquals(secondCar, parkingBoy.fetch(secondTicket));
    }
    
    @Test
    public void should_throw_unRecognizedParkingTicketException_when_fetch_given_wrong_ticket() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Ticket wrongTicket = new Ticket();
        // When
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(wrongTicket));
        // Then
        assertEquals("Unrecognized parking ticket", exception.getMessage());
    }

}
