package ru.job4j.ood.ocp;

import ru.job4j.ood.srp.reports.MemStore;

/**
 * Класс нарушает принцип ОСР, т.к.
 * метод нельзя будет использовать для другого типа хранилища.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Store {
    public void load(MemStore store) {
    }
}
