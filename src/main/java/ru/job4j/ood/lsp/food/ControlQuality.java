package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControlQuality {
    private final List<Store> stores;
    private final ShelfLifeCalculator shelfLifeCalculator = new ShelfLifeCalculator();

    public ControlQuality(List<Store> stores) {
        Objects.requireNonNull(stores, "stores must not be null");
        if (stores.isEmpty()) {
            throw new IllegalArgumentException("stores must not be empty");
        }
        this.stores = List.copyOf(stores);
    }

    public void distribute(Food food) {
        Objects.requireNonNull(food, "food must not be null");
        for (Store store : stores) {
            if (store.add(food)) {
                return;
            }
        }
        throw new IllegalStateException("No store accepted food: " + food.getName());
    }

    public void distribute(List<Food> foods) {
        Objects.requireNonNull(foods, "foods must not be null");
        LocalDate currentDate = LocalDate.now();
        foods.forEach(food -> shelfLifeCalculator.usagePercent(food, currentDate));
        List<Food> undistributed = foods;
        for (Store store : stores) {
            undistributed = store.add(undistributed);
        }
        if (!undistributed.isEmpty()) {
            throw new IllegalStateException("No store accepted food: " + undistributed.get(0).getName());
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
            foods.addAll(store.extractAll());
        }
        distribute(foods);
    }
}
