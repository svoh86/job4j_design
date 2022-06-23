package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает хранилище Trash
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Trash implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public void save(Food food) {
        if (PercentDate.percent(food) >= 100) {
            foods.add(food);
        }
    }

    @Override
    public List<Food> getAll() {
        return foods;
    }
}
