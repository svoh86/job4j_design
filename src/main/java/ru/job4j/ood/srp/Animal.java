package ru.job4j.ood.srp;

/**
 * Интерфей нарушает принцип SRP,
 * т.к. не все животные могут одновременно летать, бегать и плавать
 */
public interface Animal {
    void fly();

    void run();

    void swim();
}
