package ru.job4j.ood.lsp.parking;

/**
 * @author Svistunov Mikhail
 * @version 1.1
 * Добавил константу с размером легковой машины.
 */
public class Car extends Vehicle {
    public static final int SIZE = 1;

    public Car(String name) {
        super(name, SIZE);
    }
}
