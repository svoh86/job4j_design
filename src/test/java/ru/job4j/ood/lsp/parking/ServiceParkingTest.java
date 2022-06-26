package ru.job4j.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ServiceParkingTest {

    @Test
    public void whenCarParking() {
        Vehicle car = new Car("Nissan");
        Vehicle car2 = new Car("Toyota");
        Parking carParking = new CarParking(5);
        List<Parking> parkingList = List.of(carParking);
        ServiceParking serviceParking = new ServiceParking(parkingList);
        serviceParking.distribution(car);
        serviceParking.distribution(car2);
        assertEquals(carParking.getAll(), List.of(car, car2));
    }

    @Test
    public void whenTruckParking() {
        Vehicle car = new Truck("Nissan", 3);
        Parking truckParking = new TruckParking(5);
        List<Parking> parkingList = List.of(truckParking);
        ServiceParking serviceParking = new ServiceParking(parkingList);
        serviceParking.distribution(car);
        assertEquals(truckParking.getAll(), List.of(car));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTruckWrongSize() {
        Vehicle car = new Truck("Nissan", 1);
    }

    @Test
    public void whenTruckParking5Truck3WithSize2() {
        Vehicle car = new Truck("Nissan", 2);
        Vehicle car2 = new Truck("Toyota", 2);
        Vehicle car3 = new Truck("Volvo", 2);
        Parking truckParking = new TruckParking(2);
        List<Parking> parkingList = List.of(truckParking);
        ServiceParking serviceParking = new ServiceParking(parkingList);
        serviceParking.distribution(car);
        serviceParking.distribution(car2);
        serviceParking.distribution(car3);
        assertEquals(truckParking.getAll(), List.of(car, car2));
    }

    @Ignore
    @Test
    public void whenCarParking2AndTuckParking2Truck3WithSize2() {
        Vehicle car = new Truck("Nissan", 2);
        Vehicle car2 = new Truck("Toyota", 2);
        Vehicle car3 = new Truck("Volvo", 2);
        Parking carParking = new CarParking(6);
        Parking truckParking = new TruckParking(2);
        List<Parking> parkingList = List.of(truckParking, carParking);
        ServiceParking serviceParking = new ServiceParking(parkingList);
        serviceParking.distribution(car);
        serviceParking.distribution(car2);
        serviceParking.distribution(car3);
        assertEquals(truckParking.getAll(), List.of(car, car2));
        assertEquals(carParking.getAll(), List.of(car3));
    }

    @Test
    public void whenParking2Car3() {
        Vehicle car = new Car("Nissan");
        Vehicle car2 = new Car("Toyota");
        Vehicle car3 = new Car("Volvo");
        Parking carParking = new CarParking(2);
        List<Parking> parkingList = List.of(carParking);
        ServiceParking serviceParking = new ServiceParking(parkingList);
        serviceParking.distribution(car);
        serviceParking.distribution(car2);
        serviceParking.distribution(car3);
        assertEquals(carParking.getAll(), List.of(car, car2));
    }
}