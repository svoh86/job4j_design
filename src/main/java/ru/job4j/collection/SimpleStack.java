package ru.job4j.collection;

import ru.job4j.collection.forwardlinked.ForwardLinked;

/**
 * Класс, используя контейнер на базе связанного списка, создает контейнер Stack
 * LIFO
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * метод возвращает значение и удаляет его из начала коллекции
     *
     * @return возвращает значение
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * метод помещает значение в начало коллекции.
     *
     * @param value значение
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}
