package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает парковочные места для легковых авто.
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * Добавил поля с количеством парковочных мест.
 * Инициализация списка должна идти в конструкторе после присвоения значения capacity.
 */
public class CarParking implements Parking {
    private final List<Vehicle> vehicles;
    private int capacity;

    public CarParking(int capacity) {
        this.capacity = capacity;
        this.vehicles = new ArrayList<>(capacity);
    }

    @Override
    public boolean park(Vehicle vehicle) {
        boolean flag = false;
        int size = vehicle.getSize();
        if (capacity < size) {
            return false;
        }
        if (size == Car.SIZE) {
            vehicles.add(vehicle);
            capacity -= size;
            flag = true;
        }
        if (size > Car.SIZE) {
            vehicles.add(vehicle);
            capacity -= size;
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Vehicle> getAll() {
        return List.copyOf(vehicles);
    }
}
