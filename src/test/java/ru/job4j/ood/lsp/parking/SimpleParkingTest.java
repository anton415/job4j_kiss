package ru.job4j.ood.lsp.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class SimpleParkingTest {
    @Test
    public void whenParkPassengerCarThenUseOnlyPassengerPlace() {
        Parking parking = parking(2, 1);
        Car car = new TestCar("A001AA", 1);

        boolean parked = parking.park(car);

        assertTrue(parked);
        assertEquals(1, parking.getFreePassengerPlaces());
        assertEquals(1, parking.getFreeTruckPlaces());
        assertEquals(1, parking.findAll().size());
        assertTrue(parking.findAll().contains(car));
    }

    @Test
    public void whenNoPassengerPlacesThenPassengerCarIsNotParked() {
        Parking parking = parking(0, 1);
        Car car = new TestCar("A001AA", 1);

        boolean parked = parking.park(car);

        assertFalse(parked);
        assertEquals(0, parking.getFreePassengerPlaces());
        assertEquals(1, parking.getFreeTruckPlaces());
        assertTrue(parking.findAll().isEmpty());
    }

    @Test
    public void whenParkTruckCarThenUseTruckPlace() {
        Parking parking = parking(3, 1);
        Car truck = new TestCar("T001TT", 3);

        boolean parked = parking.park(truck);

        assertTrue(parked);
        assertEquals(3, parking.getFreePassengerPlaces());
        assertEquals(0, parking.getFreeTruckPlaces());
        assertEquals(1, parking.findAll().size());
        assertTrue(parking.findAll().contains(truck));
    }

    @Test
    public void whenNoTruckPlacesThenTruckCarUsesPassengerPlaces() {
        Parking parking = parking(4, 0);
        Car truck = new TestCar("T001TT", 3);

        boolean parked = parking.park(truck);

        assertTrue(parked);
        assertEquals(1, parking.getFreePassengerPlaces());
        assertEquals(0, parking.getFreeTruckPlaces());
        assertEquals(1, parking.findAll().size());
        assertTrue(parking.findAll().contains(truck));
    }

    @Test
    public void whenNoSuitablePlacesThenTruckCarIsNotParked() {
        Parking parking = parking(2, 0);
        Car truck = new TestCar("T001TT", 3);

        boolean parked = parking.park(truck);

        assertFalse(parked);
        assertEquals(2, parking.getFreePassengerPlaces());
        assertEquals(0, parking.getFreeTruckPlaces());
        assertTrue(parking.findAll().isEmpty());
    }

    @Test
    public void whenPassengerAndTruckCapacitiesCreatedThenCountersAreAvailable() {
        Parking parking = parking(5, 2);

        assertEquals(5, parking.getPassengerPlacesCapacity());
        assertEquals(2, parking.getTruckPlacesCapacity());
        assertEquals(5, parking.getFreePassengerPlaces());
        assertEquals(2, parking.getFreeTruckPlaces());
        assertNotNull(parking.findAll());
        assertTrue(parking.findAll().isEmpty());
    }

    private Parking parking(int passengerPlaces, int truckPlaces) {
        try {
            Class<?> clazz = Class.forName("ru.job4j.ood.lsp.parking.SimpleParking");
            return (Parking) clazz.getConstructor(int.class, int.class)
                    .newInstance(passengerPlaces, truckPlaces);
        } catch (ReflectiveOperationException e) {
            fail("Implement class ru.job4j.ood.lsp.parking.SimpleParking with constructor (int passengerPlaces, int truckPlaces)", e);
            return null;
        }
    }

    private record TestCar(String number, int size) implements Car {
        @Override
        public String getNumber() {
            return number;
        }

        @Override
        public int getSize() {
            return size;
        }
    }
}
