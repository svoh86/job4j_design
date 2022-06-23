package ru.job4j.ood.lsp.foodstore;

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
}
