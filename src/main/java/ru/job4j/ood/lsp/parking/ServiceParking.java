package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Класс в зависимости от размера машины распределяет машины по парковочным местам.
 *
 * @author Svistunov Mikhail
 * @version 1.2
 * Добавил поля с количеством парковочных мест для легковых и грузовых.
 * Перенес поля в реализации Parking.
 */
public class ServiceParking {
    private final List<Parking> parkingList;

    public ServiceParking(List<Parking> parkings) {
        this.parkingList = parkings;
    }

    public void distribution(Vehicle vehicle) {
        for (Parking parking : parkingList) {
            parking.park(vehicle);
        }
    }
}
