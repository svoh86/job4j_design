package ru.job4j.ood.srp.reports.generator;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.Store;
import ru.job4j.ood.srp.reports.utility.Constants;

import java.util.function.Predicate;

/**
 * Класс генерирует отчет для бухгалтерии
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * Добавлен класс констант
 */
public class AccountingGenerator implements GeneratorReport {
    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(Constants.DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(Constants.DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary() * Constants.CONVERTER).append(" rub").append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
