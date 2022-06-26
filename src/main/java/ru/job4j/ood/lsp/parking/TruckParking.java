package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает парковочные места для грузовых авто.
 *
 * @author Svistunov Mikhail
 * @version 1.2
 * Добавил поля с количеством парковочных мест.
 * Метод park() в TruckParking учитывает, что нельзя парковать легковые машины.
 */
public class TruckParking implements Parking {
    private final List<Vehicle> vehicles;
    private int capacity;

    private int countSize = 0;

    public TruckParking(int capacity) {
        this.vehicles = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        boolean flag = false;
        int size = vehicle.getSize();
        if (Car.SIZE == size) {
            return false;
        }
        if (size > Car.SIZE && capacity > 0) {
            vehicles.add(vehicle);
            capacity--;
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Vehicle> getAll() {
        return List.copyOf(vehicles);
    }
}
