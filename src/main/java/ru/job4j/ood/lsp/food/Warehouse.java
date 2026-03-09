package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.function.Supplier;

public class Warehouse extends AbstractStore {
    public Warehouse() {
    }

    public Warehouse(Supplier<LocalDate> currentDateSupplier) {
        super(currentDateSupplier);
    }

    @Override
    public boolean accepts(Food food) {
        return usagePercent(food) < 25D;
    }
}
