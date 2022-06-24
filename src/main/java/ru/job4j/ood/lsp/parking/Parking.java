package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Интерфейс описывает парковочные места машин.
 * Методы для сохранения в списке парковочных мест по условию
 * и получения всех машин.
 */
public interface Parking {
    boolean save(Vehicle vehicle);

    List<Vehicle> getAll();
}
