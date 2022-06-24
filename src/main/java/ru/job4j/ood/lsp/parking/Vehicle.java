package ru.job4j.ood.lsp.parking;

import java.util.Objects;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public abstract class Vehicle {
    private String name;
    private int size;

    public Vehicle(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return size == vehicle.size && Objects.equals(name, vehicle.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }

    @Override
    public String toString() {
        return "Vehicle{" + "name='" + name + '\'' + ", size=" + size + '}';
    }
}
