package ru.job4j.generic;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public abstract class Base {
    private final String id;

    protected Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}