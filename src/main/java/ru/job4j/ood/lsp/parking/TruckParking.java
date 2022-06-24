package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает парковочные места для грузовых авто.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class TruckParking implements Parking {
    private final List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public boolean save(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<Vehicle> getAll() {
        return null;
    }
}
