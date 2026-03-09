package ru.job4j.ood.lsp.food;

import java.util.List;

public interface Store {
    boolean add(Food food);

    boolean accepts(Food food);

    List<Food> findAll();

    List<Food> extractAll();
}
