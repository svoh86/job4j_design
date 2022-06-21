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
        StringBuilder json = new StringBuilder();
        final Gson gson = new GsonBuilder().create();
        for (Employee employee : store.findBy(filter)) {
            json.append(gson.toJson(employee));
        }
        return json.toString();
    }
}
