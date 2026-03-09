package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class AbstractStore implements Store {
    private final List<Food> foods = new ArrayList<>();
    /* Источник текущей даты вынесен отдельно, чтобы удобно тестировать граничные случаи. */
    private final Supplier<LocalDate> currentDateSupplier;

    protected AbstractStore() {
        this(LocalDate::now);
    }

    protected AbstractStore(Supplier<LocalDate> currentDateSupplier) {
        this.currentDateSupplier = Objects.requireNonNull(currentDateSupplier, "currentDateSupplier must not be null");
    }

    @Override
    public boolean add(Food food) {
        Objects.requireNonNull(food, "food must not be null");
        if (!accepts(food)) {
            return false;
        }
        /* Перед сохранением наследник может изменить состояние продукта, например применить скидку. */
        prepare(food);
        foods.add(food);
        return true;
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(foods);
    }

    @Override
    public List<Food> extractAll() {
        /* Нужен для повторной сортировки: забираем все продукты и очищаем текущее хранилище. */
        List<Food> extracted = new ArrayList<>(foods);
        foods.clear();
        return extracted;
    }

    protected LocalDate currentDate() {
        return currentDateSupplier.get();
    }

    protected double usagePercent(Food food) {
        return food.getShelfLifeUsagePercent(currentDate());
    }

    protected void prepare(Food food) {
        /* По умолчанию продукт кладется как есть. */
    }
}
