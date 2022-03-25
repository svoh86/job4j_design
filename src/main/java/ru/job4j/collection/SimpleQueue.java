package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс описывает термин FIFO - first input first output. Первый пришел, первый ушел.
 * Организация данных - очередь. Очередь на двух стеках
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SimpleQueue<T> {
    /**
     * in - стэк, в который кладем значения
     * out - стэк, в который перекладываем значения
     * sizeIn - размер стэка in
     * sizeOut - размер стэка out
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn;
    private int sizeOut;

    /**
     * метод возвращает первое значение и удаляет его из коллекции,
     * перекладываем значения из in в out,
     * меняем размер стэков,
     * забираем верхний их out
     *
     * @return первое значение из коллекции
     */
    public T poll() {
        if (sizeIn == 0 && sizeOut == 0) {
            throw new NoSuchElementException();
        }
        if (sizeOut == 0) {
            while (sizeIn > 0) {
                out.push(in.pop());
                sizeIn--;
                sizeOut++;
            }
        }
        sizeOut--;
        return out.pop();
    }

    /**
     * метод помещает значение в конец
     * и увеличивает размер стэка
     *
     * @param value значение
     */
    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
