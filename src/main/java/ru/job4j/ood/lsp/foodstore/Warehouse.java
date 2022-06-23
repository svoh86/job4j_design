package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает хранилище Warehouse
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Warehouse implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public void save(Food food) {
        if (PercentDate.percent(food) < 25) {
            foods.add(food);
        }
    }

    @Override
    public List<Food> getAll() {
        return foods;
    }
}
