package ru.job4j.ood.srp.reports.utility;

import java.text.SimpleDateFormat;

/**
 * Служебный класс для констант.
 * Приватный конструктор предотвращает появление экземпляра этого класса.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Constants {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final double CONVERTER = 56.7;

    private Constants() {
    }
}
