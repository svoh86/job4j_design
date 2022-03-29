package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс описывает реализацию структуры данных HashMap
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод добавляет элемент. Разрешение коллизий не реализовано.
     * Проверяем вместимость таблицы. Определяем индекс.
     * Если место в таблице не занято, то добавляем в таблицу по индексу
     * ключ и значение. Увеличиваем счетчики кол-ва элементов и изменений.
     *
     * @param key   ключ
     * @param value значение
     * @return true, если произошло добавление; false, если ячейка занята
     */
    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = checkKey(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод реализует хэш-функцию
     *
     * @param hashCode хэш-код
     * @return хэш
     */
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    /**
     * Метод определяет индекс для таблицы
     *
     * @param hash значение хэш-кода
     * @return индекс
     */
    private int indexFor(int hash) {
        return hash(hash) & (capacity - 1);
    }

    /**
     * Метод проверяет значение ключа на null
     *
     * @param key ключ
     * @return 0 или значение индекса таблицы
     */
    private int checkKey(K key) {
        return key == null ? 0 : indexFor(key.hashCode());
    }

    /**
     * Метод расширяет таблицу.
     * Создаем временную таблицу и сохраняем в ней старую таблицу.
     * Старую таблицу заменяем новой в 2 раза больше.
     * Циклом идем по временной таблице. Если клетка таблицы не null, тогда
     * записываем её ключ и значение в новую таблицу через метод put(K key, V value)
     */
    private void expand() {
        MapEntry<K, V>[] tempTable = table;
        capacity = capacity * 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> cell : tempTable) {
            if (cell != null) {
                put(cell.key, cell.value);
                count--;
            }
        }
    }

    /**
     * Метод получает значение по ключу.
     * Определяем индекс по ключу.
     * Если ячейка null или ключи не равны по equals(),
     * тогда возвращаем null, иначе значение.
     *
     * @param key ключ
     * @return значение или  null
     */
    @Override
    public V get(K key) {
        int index = checkKey(key);
        return table[index] == null || !table[index].key.equals(key)
                ? null : table[index].value;
    }

    /**
     * Метод удаляет значение по ключу.
     * Определяем индекс по ключу.
     * Если ячейка !null и ключи равны по equals(),
     * тогда удаляем значение, иначе false.
     *
     * @param key ключ
     * @return true, если удалил
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = checkKey(key);
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int cursor = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                while (table[cursor] == null && cursor < count) {
                    cursor++;
                }
                return cursor < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}