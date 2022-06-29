package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс в зависимости от срока годности перекладывает продукты в хранилища.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class ControlQuality {
    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void distribution(Food food) {
        for (Store store : storeList) {
            store.save(food);
        }
    }

    /**
     * Метод извлекает все продукты и перераспределять их заново.
     * При этом очищаем список продуктов, которые были там ранее.
     */
    public void resort() {
        List<Food> allFoods = new ArrayList<>();
        for (Store store : storeList) {
            allFoods.addAll(store.getAll());
            store.clear();
        }
        for (Food food : allFoods) {
            distribution(food);
        }
    }
}
