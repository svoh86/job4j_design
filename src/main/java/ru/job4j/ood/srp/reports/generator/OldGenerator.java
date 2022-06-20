package ru.job4j.ood.srp.reports.generator;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.Store;

import java.util.function.Predicate;

/**
 * Класс генерирует старый отчет
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class OldGenerator implements GeneratorReport {
    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
