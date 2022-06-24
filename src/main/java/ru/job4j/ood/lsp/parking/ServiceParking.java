package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Класс в зависимости от размера машины распределяет машины по парковочным местам.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class ServiceParking {
    private final List<Parking> parkingList;

    public ServiceParking(List<Parking> parkings) {
        this.parkingList = parkings;
    }

    public void distribution(Vehicle vehicle) {

    }
}
