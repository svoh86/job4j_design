package ru.job4j.gc;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class User {
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Deleted %d %s%n", age, name);
    }
}
