package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.2. Создать итератор четные числа
 * Класс описывает итератор, который возвращающий только четные цифры
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Метод проверяет, что в массиве есть четные числа
     *
     * @return true или  false
     */
    @Override
    public boolean hasNext() {
        boolean rsl = false;
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                rsl = true;
                break;
            }
            index++;
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}