package ru.job4j.ood.srp.reports;

import org.junit.Test;
import ru.job4j.ood.srp.reports.generator.*;
import ru.job4j.ood.srp.reports.utility.Constants;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        GeneratorReport generate = new OldGenerator();
        Report engine = new ReportEngine(store, generate);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenDeveloperDepartment() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        GeneratorReport generate = new DeveloperGenerator();
        Report engine = new ReportEngine(store, generate);
        StringBuilder expect = new StringBuilder()
                .append("<head>").append("Name; Hired; Fired; Salary;").append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(worker.getName()).append(";")
                .append(worker.getHired().getTime()).append(";")
                .append(worker.getFired().getTime()).append(";")
                .append(worker.getSalary()).append(";")
                .append("</body>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountingDepartment() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        GeneratorReport generate = new AccountingGenerator();
        Report engine = new ReportEngine(store, generate);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() * 56.7).append(" rub").append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRDepartment() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Fedor", now, now, 200);
        Employee worker3 = new Employee("Petr", now, now, 50);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        GeneratorReport generate = new HRGenerator();
        Report engine = new ReportEngine(store, generate);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}