package ru.job4j.ood.isp;

/**
 * Интерфей нарушает принцип ISP,
 * т.к. не все животные могут одновременно летать, бегать и плавать
 */
public interface Animal {
    void fly();

    void run();

    void swim();
}
