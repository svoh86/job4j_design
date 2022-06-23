package ru.job4j.ood.srp.reports.generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.Store;

import java.util.function.Predicate;

/**
 * Класс генерирует отчет в формате json.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class JSONGenerator implements GeneratorReport {
    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(store.findBy(filter));
    }
}
