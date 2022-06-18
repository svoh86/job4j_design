package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Класс для поиска максимального и минимального элемента
 * по критерию java.util.Comparator.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getT(value, (i, rsl) -> comparator.compare(value.get(i), rsl) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getT(value, (i, rsl) -> comparator.compare(value.get(i), rsl) < 0);
    }

    /**
     * Метод описывает поиск элемента в листе.
     * Минимум или максимум - передаем через predicate
     * в соответсвующих методах.
     *
     * @param value     лист
     * @param predicate условие
     * @param <T>       тип
     * @return объект <T>
     */
    private <T> T getT(List<T> value, BiPredicate<Integer, T> predicate) {
        T rsl = null;
        if (!value.isEmpty()) {
            rsl = value.get(0);
            for (int i = 1; i < value.size(); i++) {
                if (predicate.test(i, rsl)) {
                    rsl = value.get(i);
                }
            }
        }
        return rsl;
    }
}
