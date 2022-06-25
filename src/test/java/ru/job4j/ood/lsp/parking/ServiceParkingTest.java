package ru.job4j.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ServiceParkingTest {
    Parking carParking = new CarParking();
    Parking truckParking = new TruckParking();
    List<Parking> parkingList = List.of(carParking, truckParking);

    @Ignore
    @Test
    public void whenCarParking() {
        Vehicle car = new Car("Nissan");
        Vehicle car2 = new Car("Toyota");
        ServiceParking serviceParking = new ServiceParking(parkingList, 5, 3);
        serviceParking.distribution(car);
        serviceParking.distribution(car2);
        assertEquals(carParking.getAll(), List.of(car, car2));
    }

    @Ignore
    @Test
    public void whenTruckParking() {
        Vehicle car = new Truck("Nissan", 3);
        ServiceParking serviceParking = new ServiceParking(parkingList, 3, 5);
        serviceParking.distribution(car);
        assertEquals(truckParking.getAll(), List.of(car));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTruckWrongSize() {
        Vehicle car = new Truck("Nissan", 1);
    }

    @Ignore
    @Test
    public void whenTruckParking5Truck3WithSize2() {
        Vehicle car = new Truck("Nissan", 2);
        Vehicle car2 = new Truck("Toyota", 2);
        Vehicle car3 = new Truck("Volvo", 2);
        ServiceParking serviceParking = new ServiceParking(parkingList, 0, 5);
        serviceParking.distribution(car);
        serviceParking.distribution(car2);
        serviceParking.distribution(car3);
        assertEquals(truckParking.getAll(), List.of(car, car2));
    }

    @Ignore
    @Test
    public void whenCarParking1AndTuckParking5Truck3WithSize2() {
        Vehicle car = new Truck("Nissan", 2);
        Vehicle car2 = new Truck("Toyota", 2);
        Vehicle car3 = new Truck("Volvo", 2);
        ServiceParking serviceParking = new ServiceParking(parkingList, 1, 5);
        serviceParking.distribution(car);
        serviceParking.distribution(car2);
        serviceParking.distribution(car3);
        assertEquals(truckParking.getAll(), List.of(car, car2, car3));
    }

    @Ignore
    @Test
    public void whenParking2Car3() {
        Vehicle car = new Car("Nissan");
        Vehicle car2 = new Car("Toyota");
        Vehicle car3 = new Car("Volvo");
        ServiceParking serviceParking = new ServiceParking(parkingList, 2, 5);
        serviceParking.distribution(car);
        serviceParking.distribution(car2);
        serviceParking.distribution(car3);
        assertEquals(truckParking.getAll(), List.of(car, car2));
    }
}