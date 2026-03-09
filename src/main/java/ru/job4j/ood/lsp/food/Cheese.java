package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class Cheese extends Food {
    public Cheese(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
