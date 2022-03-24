package ru.job4j.collection;

import java.util.*;

/**
 * Класс описывает реализацию списка на основе динамического массива, аналог ArrayList.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        increase();
        container[size++] = value;
    }

    private void increase() {
        if (size == container.length && container.length != 0) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        if (size == container.length && container.length == 0) {
            container = Arrays.copyOf(container, container.length + 10);
        }
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        modCount++;
        T rsl = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[cursor++];
            }
        };
    }
}