package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        Objects.requireNonNull(stores, "stores must not be null");
        if (stores.isEmpty()) {
            throw new IllegalArgumentException("stores must not be empty");
        }
        this.stores = List.copyOf(stores);
    }

    public void distribute(Food food) {
        Objects.requireNonNull(food, "food must not be null");
        /* Контроллер ничего не знает о типах хранилищ: он просто пробует каждое по контракту Store. */
        for (Store store : stores) {
            if (store.add(food)) {
                return;
            }
        }
        throw new IllegalStateException("No store accepted food: " + food.getName());
    }

    public void distribute(List<Food> foods) {
        Objects.requireNonNull(foods, "foods must not be null");
        foods.forEach(this::distribute);
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
            foods.addAll(store.extractAll());
        }
        /* После очистки хранилищ распределяем продукты заново по актуальной дате. */
        distribute(foods);
    }
}
