package ru.job4j.ood.srp;

/**
 * Класс нарушает принцип SRP,
 * т.к. совмещает в себе модель данных и работу с БД.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Article {
    private int id;
    private String name;

    public Article(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void loadFromDB() {
    }

    public void saveToDB() {
    }
}
