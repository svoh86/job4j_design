package ru.job4j.ood.lsp.parking;

/**
 * @author Svistunov Mikhail
 * @version 1.1
 * Добавил валидацию на размер грузовика, при его создании.
 */
public class Truck extends Vehicle {
    public Truck(String name, int size) {
        super(name, size);
        if (size <= Car.SIZE) {
            throw new IllegalArgumentException("Truck size must be greater than 1");
        }
    }
}
