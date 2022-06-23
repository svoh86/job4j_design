package ru.job4j.ood.lsp.foodstore;

import java.util.List;

/**
 * Интерфейс описывает хранилище продуктов.
 * Методы для сохранения в списке продуктов по условию
 * и получения всех продуктов.
 */
public interface Store {
    void save(Food food);

    List<Food> getAll();
}
