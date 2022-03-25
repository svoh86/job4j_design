package ru.job4j.collection.forwardlinked;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс описывает одгосвязный список
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rsl = head.value;
        Node<T> newHead = head.next;
        head.next = null;
        head.value = null;
        head = newHead;
        return rsl;
    }

    /**
     * Метод переворачивает односвязный список.
     * Валидация, что список не пуст и имеет больше одного значения.
     * Указатель на предыдущий, текущий и следующий элементы.
     * следующий = текущий.next;
     * текущий.next = предыдущий;  разворот ссылки
     * предыдущий = текущий;
     * текущий = следующий;
     *
     * @return true or false
     */
    public boolean revert() {
        if (head != null && head.next != null) {
            Node<T> preNode = null;
            Node<T> currentNode = head;
            while (currentNode != null) {
                Node<T> nextNode = currentNode.next;
                currentNode.next = preNode;
                preNode = currentNode;
                currentNode = nextNode;
            }
            head = preNode;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ForwardLinked.Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}