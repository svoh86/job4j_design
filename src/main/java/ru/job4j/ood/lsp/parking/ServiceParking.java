package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Класс в зависимости от размера машины распределяет машины по парковочным местам.
 *
 * @author Svistunov Mikhail
 * @version 1.2
 * Добавил поля с количеством парковочных мест для легковых и грузовых.
 * Перенес поля в реализации Parking.
 * Здесь не совсем применим данный шаблон, так как есть одно общее условие у Стратегий,
 * поэтому было бы достаточно создать абстракцию Транспорта + 2 его реализации
 * и абстракция Парковки + одна реализация с методов add, в котором 3 условия для добавления.
 */
public class ServiceParking {
    private final List<Parking> parkingList;

    public ServiceParking(List<Parking> parkings) {
        this.parkingList = parkings;
    }

    public void distribution(Vehicle vehicle) {
        for (Parking parking : parkingList) {
            if (parking.park(vehicle)) {
                break;
            }
        }
    }
}
