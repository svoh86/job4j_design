package ru.job4j.ood.ocp;

/**
 * Класс нарушает принцип ОСР, т.к.
 * записываем в файл, но если надо будет записать в БД, то потребуется изменить класс, добавив метод.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Writer {
    public void saveFile() {
    }
}
