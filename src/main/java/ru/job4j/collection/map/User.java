package ru.job4j.collection.map;

import java.util.Calendar;

/**
 * Класс описывает модель данных Пользователя
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class User {
    private String name;
    private int children;
    Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}