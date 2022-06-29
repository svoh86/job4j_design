package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает хранилище Trash
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * getAll() нарушал инкапсуляцию, т.к. получаем доступ к хранилищу и
 * можем добавлять невалидный продукты напрямую. Теперь возвращаем копию.
 * Добавлены константы.
 * save() переделан на boolean.
 * Класс PercentDate заменен на дефолтный метод в интерфейсе Store.
 * Добавлен метод очистки листа.
 */
public class Trash implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean save(Food food) {
        boolean flag = false;
        if (percent(food) >= Constants.UPPER_LIMIT) {
            foods.add(food);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Food> getAll() {
        return List.copyOf(foods);
    }

    @Override
    public void clear() {
        foods.clear();
    }
}
