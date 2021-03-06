package ru.job4j.collection.forwardlinked;

import org.w3c.dom.Node;

import java.util.*;

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
        boolean rsl = head != null && head.next != null;
        if (rsl) {
            Node<T> preNode = null;
            Node<T> currentNode = head;
            while (currentNode != null) {
                Node<T> nextNode = currentNode.next;
                currentNode.next = preNode;
                preNode = currentNode;
                currentNode = nextNode;
            }
            head = preNode;
        }
        return rsl;
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

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListIterator<Integer> i = list.listIterator();
        while (i.hasNext()) {
            Integer value = i.next();
            if (value <= 5) {
                list.add(value * value);
            }
            i.next();
        }
    }
}