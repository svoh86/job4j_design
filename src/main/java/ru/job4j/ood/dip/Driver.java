package ru.job4j.ood.dip;

/**
 * Класс write() нарушает принцип DIP, т.к. метод drive зависит от от реализации.
 * Надо создать абстракцию Vehicle, от которой наследоваться Car.
 * В метод drive передавать параметром Vehicle.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Driver {
    void drive(Car car) {
    }
}

class Car {
    private String name;
}
