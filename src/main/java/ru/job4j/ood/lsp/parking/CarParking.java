package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает парковочные места для легковых авто.
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * Добавил поля с количеством парковочных мест.
 */
public class CarParking implements Parking {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private int amountCarParking;

    public CarParking(int amountCarParking) {
        this.amountCarParking = amountCarParking;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        boolean flag = false;
        int size = vehicle.getSize();
        if (size == Car.SIZE && amountCarParking >= size) {
            vehicles.add(vehicle);
            amountCarParking -= size;
            flag = true;
        }
        if (size > Car.SIZE && amountCarParking >= size) {
            vehicles.add(vehicle);
            amountCarParking -= size;
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Vehicle> getAll() {
        return List.copyOf(vehicles);
    }
}
