package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public interface Store {
    boolean add(Food food);

    default List<Food> add(List<Food> foods) {
        List<Food> rejected = new ArrayList<>();
        for (Food food : foods) {
            if (!add(food)) {
                rejected.add(food);
            }
        }
        return rejected;
    }

    boolean accepts(Food food);

    List<Food> findAll();

    List<Food> extractAll();
}
