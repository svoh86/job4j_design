package ru.job4j.ood.lsp;

/**
 * Класс нарушеат принцип LSP, т.к.
 * в классе OtherSchool ослаблено постусловие.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class School {

    public boolean firstClass(int firstClassAge) {
        return firstClassAge > 7;
    }
}

class OtherSchool extends School {

    @Override
    public boolean firstClass(int firstClassAge) {
        return true;
    }
}
