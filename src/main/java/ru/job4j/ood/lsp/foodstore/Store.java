package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Интерфейс описывает хранилище продуктов.
 * Методы для сохранения в списке продуктов по условию
 * и получения всех продуктов.
 * Дефолтный метод высчитывает процент от срока годности.
 * Добавлен метод очистки листа.
 */
public interface Store {
    boolean save(Food food);

    List<Food> getAll();

    void clear();

    default double percent(Food food) {
        LocalDate currentDate = LocalDate.now();
        LocalDate createDate = food.getCreateDate();
        LocalDate expiryDate = food.getExpiryDate();
        double allDays = ChronoUnit.DAYS.between(createDate, expiryDate);
        double useDays = ChronoUnit.DAYS.between(createDate, currentDate);
        if (allDays <= 0) {
            throw new IllegalArgumentException("Expiry date less or equals create date");
        }
        return useDays / allDays * 100;
    }
}
