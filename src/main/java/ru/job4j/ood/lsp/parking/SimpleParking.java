package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Простая реализация парковки для легковых и грузовых машин.
 * Легковая машина занимает только одно легковое место.
 * Грузовая машина занимает либо одно грузовое место, либо несколько подряд идущих легковых мест.
 */
public class SimpleParking implements Parking {
    private final boolean[] passengerPlaces;
    private final int truckPlacesCapacity;
    private final List<Car> parkedCars = new ArrayList<>();
    private int freePassengerPlaces;
    private int freeTruckPlaces;

    public SimpleParking(int passengerPlaces, int truckPlaces) {
        if (passengerPlaces < 0) {
            throw new IllegalArgumentException("passengerPlaces must not be negative");
        }
        if (truckPlaces < 0) {
            throw new IllegalArgumentException("truckPlaces must not be negative");
        }
        this.passengerPlaces = new boolean[passengerPlaces];
        this.truckPlacesCapacity = truckPlaces;
        this.freePassengerPlaces = passengerPlaces;
        this.freeTruckPlaces = truckPlaces;
    }

    @Override
    public boolean park(Car car) {
        Objects.requireNonNull(car, "car must not be null");
        validateCarSize(car);
        if (isPassengerCar(car)) {
            return parkPassengerCar(car);
        }
        return parkTruckCar(car);
    }

    @Override
    public List<Car> findAll() {
        return List.copyOf(parkedCars);
    }

    @Override
    public int getPassengerPlacesCapacity() {
        return passengerPlaces.length;
    }

    @Override
    public int getTruckPlacesCapacity() {
        return truckPlacesCapacity;
    }

    @Override
    public int getFreePassengerPlaces() {
        return freePassengerPlaces;
    }

    @Override
    public int getFreeTruckPlaces() {
        return freeTruckPlaces;
    }

    private void validateCarSize(Car car) {
        if (car.getSize() < 1) {
            throw new IllegalArgumentException("car size must be greater than zero");
        }
    }

    private boolean isPassengerCar(Car car) {
        return car.getSize() == 1;
    }

    private boolean parkPassengerCar(Car car) {
        int start = findConsecutivePassengerPlaces(1);
        if (start == -1) {
            return false;
        }
        occupyPassengerPlaces(start, 1);
        parkedCars.add(car);
        return true;
    }

    private boolean parkTruckCar(Car car) {
        /* Для грузовика приоритетно используем профильное место. */
        if (freeTruckPlaces > 0) {
            freeTruckPlaces--;
            parkedCars.add(car);
            return true;
        }
        /* Если грузовых мест нет, ищем подряд идущие легковые места по размеру машины. */
        int start = findConsecutivePassengerPlaces(car.getSize());
        if (start == -1) {
            return false;
        }
        occupyPassengerPlaces(start, car.getSize());
        parkedCars.add(car);
        return true;
    }

    private int findConsecutivePassengerPlaces(int requiredPlaces) {
        if (requiredPlaces > freePassengerPlaces) {
            return -1;
        }
        int consecutive = 0;
        int start = -1;
        for (int index = 0; index < passengerPlaces.length; index++) {
            if (!passengerPlaces[index]) {
                if (consecutive == 0) {
                    start = index;
                }
                consecutive++;
                if (consecutive == requiredPlaces) {
                    return start;
                }
            } else {
                consecutive = 0;
                start = -1;
            }
        }
        return -1;
    }

    private void occupyPassengerPlaces(int start, int size) {
        /* Помечаем найденный непрерывный участок как занятый одной машиной. */
        for (int index = start; index < start + size; index++) {
            passengerPlaces[index] = true;
        }
        freePassengerPlaces -= size;
    }
}
