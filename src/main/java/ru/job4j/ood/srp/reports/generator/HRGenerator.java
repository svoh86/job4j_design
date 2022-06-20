package ru.job4j.ood.srp.reports.generator;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.Store;

import java.util.List;
import java.util.function.Predicate;

/**
 * Класс генерирует отчет для HR
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class HRGenerator implements GeneratorReport {
    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees.sort((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()));

        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
