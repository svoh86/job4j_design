package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс абстрактный. Предоставляет базовый функционал для классов-наследников.
 * Описывает структуру данных типа кеш.
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * в методе get() V value = cache.getOrDefault(key, new SoftReference<V>(null)).get();
 */
public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Метод заполняет кэш.
     *
     * @param key   ключ
     * @param value значение
     */
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * Метод принимает ключ. Получаем стронг-ссылку из мапы
     * и проверяем, что если значения нет в памяти (==null),
     * то вызываем метод load() для получения значения.
     * Далее кладем значение вместе с ключом в кэш (мапу).
     *
     * @param key ключ
     * @return значение
     */
    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    /**
     * Абстрактный метод. Загружает данные,
     * которые потом в методе get() будут добавлены в кэш.
     * Реализация в каждом наследнике своя.
     *
     * @param key ключ
     * @return значение
     */
    protected abstract V load(K key);
}
