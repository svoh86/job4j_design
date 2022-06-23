package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает хранилище Shop
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Shop implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public void save(Food food) {
        double percent = PercentDate.percent(food);
        if (percent >= 25 && percent <= 75) {
            foods.add(food);
        }
        if (percent > 75 && percent < 100) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
            foods.add(food);
        }
    }

    @Override
    public List<Food> getAll() {
        return foods;
    }
}
