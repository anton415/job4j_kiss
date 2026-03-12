package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;

public class Example2 {
    // Нарушение DIP: поле объявлено конкретной реализацией ArrayList,
    // вместо абстракции List.
    private final ArrayList<String> items = new ArrayList<>();

    public void add(String item) {
        items.add(item);
    }

    public List<String> all() {
        return items;
    }
}
