package com.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.parkinglot.strategy.ParkingStrategyEnum;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SuperParkingBoyTest{
    @Test
    public void should_park_in_first_when_park_given_two_parking_lot_has_same_empty_position() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = createParkingBoyWithTwoParkingLotAndPreferMaxAvailablePositionRate(firstParkingLot, secondParkingLot);
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
        ParkingBoy parkingBoy = createParkingBoyWithTwoParkingLotAndPreferMaxAvailablePositionRate(firstParkingLot, secondParkingLot);
        // When
        Ticket firstTicket = parkingBoy.park(new Car());
        Ticket secondTicket = parkingBoy.park(new Car());
        ParkingLot actualFirstParkingLot = parkingBoy.getParkingLotByTicket(firstTicket);
        ParkingLot actualSecondParkingLot = parkingBoy.getParkingLotByTicket(secondTicket);
        // Then
        assertEquals(firstParkingLot, actualFirstParkingLot);
        assertEquals(secondParkingLot, actualSecondParkingLot);
    }

    @Test
    public void should_throw_unRecognizedParkingTicketException_when_fetch_given_wrong_ticket_and_smart_parking_boy() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = createParkingBoyWithTwoParkingLotAndPreferMaxAvailablePositionRate(firstParkingLot, secondParkingLot);
        Ticket wrongTicket = new Ticket();
        // When
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(wrongTicket));
        // Then
        assertEquals("Unrecognized parking ticket", exception.getMessage());
    }

    @Test
    public void should_throw_unRecognizedParkingTicketException_when_fetch_given_ticket_use_twice_and_smart_parking_boy() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = createParkingBoyWithTwoParkingLotAndPreferMaxAvailablePositionRate(firstParkingLot, secondParkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        // When
        parkingBoy.fetch(ticket);
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket), "Unrecognized parking ticket");
    }

    @Test
    public void should_return_NoAvailablePositionException_when_park_given_parking_lot_is_full_and_two_parking_lot() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(0);
        ParkingBoy parkingBoy = createParkingBoyWithTwoParkingLotAndPreferMaxAvailablePositionRate(firstParkingLot, secondParkingLot);
        Car secondCar = new Car();
        // When
        // Then
        assertThrows(
                NoAvailablePositionException.class,
                () -> parkingBoy.park(secondCar),
                "No available position.");
    }
    
    private static ParkingBoy createParkingBoyWithTwoParkingLotAndPreferMaxAvailablePositionRate(ParkingLot firstParkingLot, ParkingLot secondParkingLot) {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.setPreferParkingStrategy(ParkingStrategyEnum.MAXIMUM_AVAILABLE_POSITION_RATE);
        parkingBoy.setParkingLots(List.of(firstParkingLot, secondParkingLot));
        return parkingBoy;
    }
}
