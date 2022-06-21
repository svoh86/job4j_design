package ru.job4j.ood.lsp;

/**
 * Класс нарушеат принцип LSP, т.к.
 * в классе UserDataBase не выполнено условие базового класса.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class User {
    private String password;

    public User(String password) {
        validate(password);
        this.password = password;
    }

    public void validate(String password) {
        if (password.length() < 5) {
            throw new IllegalArgumentException("Wrong password length");
        }
    }
}

class UserDataBase extends User {
    public UserDataBase(String password) {
        super(password);
    }

    @Override
    public void validate(String password) {

    }
}

