package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Класс в зависимости от размера машины распределяет машины по парковочным местам.
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * Добавил поля с количеством парковочных мест для легковых и грузовых.
 */
public class ServiceParking {
    private final List<Parking> parkingList;
    private final int amountCarParking;
    private final int amountTruckParking;

    public ServiceParking(List<Parking> parkings, int amountCarParking, int amountTruckParking) {
        this.parkingList = parkings;
        this.amountCarParking = amountCarParking;
        this.amountTruckParking = amountTruckParking;
    }

    public void distribution(Vehicle vehicle) {

    }
}
