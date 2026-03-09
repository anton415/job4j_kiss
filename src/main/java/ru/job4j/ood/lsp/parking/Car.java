package ru.job4j.ood.lsp.parking;

/**
 * Машина, которую можно разместить на парковке.
 * Размер 1 означает легковой автомобиль, размер больше 1 - грузовой.
 */
public interface Car {
    String getNumber();

    int getSize();
}
