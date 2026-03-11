package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.function.Supplier;

public class Shop extends AbstractStore {
    public Shop() {
    }

    public Shop(Supplier<LocalDate> currentDateSupplier) {
        super(currentDateSupplier);
    }

    @Override
    public boolean accepts(Food food) {
        double usage = usagePercent(food);
        return usage >= 25D && usage < 100D;
    }

    @Override
    protected void prepare(Food food) {
        if (needsDiscount(food)) {
            food.applyDiscount();
        }
    }
}
