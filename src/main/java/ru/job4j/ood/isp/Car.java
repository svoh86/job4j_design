package ru.job4j.ood.isp;

/**
 * Интерфей нарушает принцип ISP,
 * т.к. не всем машинам нужна заправка топливом и не всем нужна зарядка батареи.
 */
public interface Car {
    void drive();

    void refueling();

    void charge();
}
