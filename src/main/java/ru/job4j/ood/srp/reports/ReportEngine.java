package ru.job4j.ood.srp.reports;

import ru.job4j.ood.srp.reports.generator.GeneratorReport;

import java.util.function.Predicate;

/**
 * Класс создает отчет в зависимости от генератора
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class ReportEngine implements Report {

    private Store store;

    private GeneratorReport generate;

    public ReportEngine(Store store, GeneratorReport generate) {
        this.store = store;
        this.generate = generate;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return generate.generate(filter, store);
    }
}
