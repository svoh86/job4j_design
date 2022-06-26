package ru.job4j.ood.isp;

/**
 * Интерфей нарушает принцип ISP,
 * т.к. не у всех средств передвижения есть мотор.
 */
public interface Vehicle {
    void drive();

    boolean startEngine();
}
