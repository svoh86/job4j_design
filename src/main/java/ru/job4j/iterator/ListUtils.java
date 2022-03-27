package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class ListUtils {
    /**
     * Метод вставляет значение в список перед индексом.
     * Проверяем индекс, создеам листератор.
     * Пока есть следующий элемент:
     * если индекс следующего элемента == индексу,
     * то добавляем значение
     *
     * @param list  список
     * @param index индекс
     * @param value значение
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод вставляет значение в список после индекса.
     * Проверяем индекс, создеам листератор.
     * Пока есть следующий элемент:
     * если индекс следующего элемента == индексу, то переводим курсор далее
     * и добавляем значение
     *
     * @param list  список
     * @param index индекс
     * @param value значение
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.next();
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод удаляет все элементы, которые удовлетворяют предикату.
     * Пока есть следующий элемент:
     * проверяем условие и удаляем элементы,
     * которые ему соответствуют.
     *
     * @param list   список
     * @param filter условие (указано в тесте)
     * @param <T>    тип
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Метод заменяет все элементы, которые удовлетворяют предикату.
     * Пока есть следующий элемент:
     * проверяем условие и заменяем элементы,
     * которые ему соответствуют.
     *
     * @param list   список
     * @param filter условие (указано в тесте)
     * @param value  значение, на которое заменяем
     * @param <T>    тип
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * Метод удаляет все элементы, которые есть в списке elements.
     * Пока есть следующий элемент:
     * проверяем условие и удаляем элементы,
     * которые ему соответствуют.
     *
     * @param list     список
     * @param elements список elements
     * @param <T>      тип
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (elements.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }
//    public static void main(String[] args) {
//        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
//        ListIterator<Integer> iterator = input.listIterator();
//        System.out.println(iterator.hasNext());
//        System.out.println(iterator.next());
//        System.out.println(iterator.nextIndex());
//        System.out.println(iterator.hasNext());
//        System.out.println(iterator.next());
//        System.out.println(iterator.hasNext());
//        System.out.println(iterator.next());
//        System.out.println(iterator.hasNext());
//    }
}