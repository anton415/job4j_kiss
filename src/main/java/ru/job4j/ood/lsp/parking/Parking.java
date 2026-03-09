package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Сервис парковки и учета размещенных машин.
 */
public interface Parking {
    boolean park(Car car);

    List<Car> findAll();

    int getPassengerPlacesCapacity();

    int getTruckPlacesCapacity();

    int getFreePassengerPlaces();

    int getFreeTruckPlaces();
}
