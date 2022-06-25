package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает парковочные места для грузовых авто.
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * Добавил поля с количеством парковочных мест.
 */
public class TruckParking implements Parking {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final int amountTruckParking;

    private int countSize = 0;

    public TruckParking(int amountTruckParking) {
        this.amountTruckParking = amountTruckParking;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        boolean flag = false;
        if (amountTruckParking >= countSize + vehicle.getSize()) {
            vehicles.add(vehicle);
            countSize += vehicle.getSize();
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Vehicle> getAll() {
        return List.copyOf(vehicles);
    }
}
