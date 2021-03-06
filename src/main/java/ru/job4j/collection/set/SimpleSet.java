package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Класс описывает реализацию коллекции Set на основе динамического массива.
 * Коллекция не хранит дубликаты.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(16);

    /**
     * Метод добавляет значение в коллекцию,
     * если такого значения там нет
     *
     * @param value значение
     * @return true or false
     */
    @Override
    public boolean add(T value) {
        boolean rsl = contains(value);
        if (!rsl) {
            set.add(value);
        }
        return !rsl;
    }

    /**
     * Метод проверяет наличие значения в коллекции
     *
     * @param value значение
     * @return true or false
     */
    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}