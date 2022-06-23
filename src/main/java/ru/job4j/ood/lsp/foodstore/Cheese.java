package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Cheese extends Food {
    public Cheese(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
