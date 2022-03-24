package ru.job4j.collection.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс описывает реализацию списка на базе связанного списка
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SimpleLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * метод добавляет элемент в конец
     *
     * @param value элемент
     */
    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * метод перебирает элементы до указанного индекса и возвращает значение из найденной ноды
     *
     * @param index индекс элемента
     * @return значение из найденной ноды
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor;
            private int expectedModCount = modCount;
            private Node<E> node = first;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E rsl = node.item;
                node = node.next;
                cursor++;
                return rsl;
            }
        };
    }
}
