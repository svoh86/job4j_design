package ru.job4j.ood.srp.reports.generator;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.Store;

import java.util.function.Predicate;

/**
 * Класс генерирует отчет для программистов
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class DeveloperGenerator implements GeneratorReport {
    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("<head>").append("Name; Hired; Fired; Salary;").append("</head>")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<body>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired().getTime()).append(";")
                    .append(employee.getFired().getTime()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</body>")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
