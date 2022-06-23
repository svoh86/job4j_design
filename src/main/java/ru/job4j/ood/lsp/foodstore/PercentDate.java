package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Класс высчитывает процент от срока годности
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class PercentDate {
    public static double percent(Food food) {
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
