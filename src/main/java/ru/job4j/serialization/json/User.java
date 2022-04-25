package ru.job4j.serialization.json;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class User {
    private final String name;
    private final String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", surname='" + surname + '\'' + '}';
    }
}
