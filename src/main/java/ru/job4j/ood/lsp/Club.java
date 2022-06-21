package ru.job4j.ood.lsp;

/**
 * Класс нарушеат принцип LSP, т.к.
 * в классе NightClub усилено предусловие.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Club {
    protected int age;

    public Club(int age) {
        this.age = age;
    }

    public void enter(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Invalid age");
        }
    }
}

class NightClub extends Club {
    public NightClub(int age) {
        super(age);
    }

    @Override
    public void enter(int age) {
        if (age < 21) {
            throw new IllegalArgumentException("Invalid age");
        }
    }
}
