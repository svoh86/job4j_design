package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.1. Итератор для двухмерного массива int[][]
 * Класс описывает итератор, который последовательно вернет элементы
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод проверяет, если ли следующий элемент, и пропускает пустые ячейки
     *
     * @return true или  false
     */
    @Override
    public boolean hasNext() {
        while (row != data.length && column == data[row].length) {
            row++;
            column = 0;
        }
        return row < data.length;
    }

    /**
     * Метод сдвигает указатель итератора
     *
     * @return значение ячейки массива
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
