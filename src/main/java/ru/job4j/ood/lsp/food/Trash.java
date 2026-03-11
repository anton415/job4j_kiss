package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.function.Supplier;

public class Trash extends AbstractStore {
    public Trash() {
    }

    public Trash(Supplier<LocalDate> currentDateSupplier) {
        super(currentDateSupplier);
    }

    @Override
    public boolean accepts(Food food) {
        return isExpired(food);
    }
}
