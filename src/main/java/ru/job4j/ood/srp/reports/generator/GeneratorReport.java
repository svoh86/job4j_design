package ru.job4j.ood.srp.reports.generator;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.Store;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public interface GeneratorReport {
//    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    String generate(Predicate<Employee> filter, Store store);
}
