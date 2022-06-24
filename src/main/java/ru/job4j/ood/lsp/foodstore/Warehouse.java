package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает хранилище Warehouse
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * getAll() нарушал инкапсуляцию, т.к. получаем доступ к хранилищу и
 * можем добавлять невалидный продукты напрямую. Теперь возвращаем копию.
 * Добавлены константы.
 * save() переделан на boolean.
 * Класс PercentDate заменен на дефолтный метод в интерфейсе Store.
 */
public class Warehouse implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean save(Food food) {
        boolean flag = false;
        if (percent(food) < Constants.LOWER_LIMIT) {
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
