package ru.job4j.ood.dip;

import java.util.List;
import java.util.Objects;

/**
 * Класс write() нарушает принцип DIP, т.к. сохранение идет в память.
 * Нужно абстрагироваться от самого хранилища, создав для него отдельный интерфейс.
 * Класс нарушает SRP, потому что представляет как саму модель заказа, так и АПИ для работы с ней.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Person {
    private String name;
    private int id;
    private List<String> students;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void add(String name) {
        students.add(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
