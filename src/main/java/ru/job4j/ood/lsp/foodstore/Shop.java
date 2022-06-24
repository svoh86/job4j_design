package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает хранилище Shop
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * getAll() нарушал инкапсуляцию, т.к. получаем доступ к хранилищу и
 * можем добавлять невалидный продукты напрямую. Теперь возвращаем копию.
 * Добавлены константы.
 * save() переделан на boolean.
 * Класс PercentDate заменен на дефолтный метод в интерфейсе Store.
 */
public class Shop implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean save(Food food) {
        boolean flag = false;
        double percent = percent(food);
        if (percent >= Constants.LOWER_LIMIT && percent <= Constants.MIDDLE_LIMIT) {
            foods.add(food);
            flag = true;
        }
        if (percent > Constants.MIDDLE_LIMIT && percent < Constants.UPPER_LIMIT) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
            foods.add(food);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Food> getAll() {
        return List.copyOf(foods);
    }
}
