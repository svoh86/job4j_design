package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает парковочные места для легковых авто.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class CarParking implements Parking {
    private final List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public boolean park(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<Vehicle> getAll() {
        return null;
    }
}
