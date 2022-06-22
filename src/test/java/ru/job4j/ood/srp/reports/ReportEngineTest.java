package ru.job4j.ood.srp.reports;

import org.junit.Test;
import ru.job4j.ood.srp.reports.generator.*;
import ru.job4j.ood.srp.reports.utility.Constants;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

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
                .append(worker.getSalary() * Constants.CONVERTER).append(" rub").append(";")
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

    @Test
    public void whenXmlReport() throws DatatypeConfigurationException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) now);
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Fedor", now, now, 200);
        store.add(worker);
        store.add(worker2);
        GeneratorReport generate = new XmlGenerator();
        Report engine = new ReportEngine(store, generate);
        String template = "<employee name=\"%s\" hired=\"%s\" fired=\"%s\" salary=\"%.1f\"/>";
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n")
                .append("<employees>")
                .append("\n    ")
                .append(String.format(Locale.US, template, worker.getName(), date, date, worker.getSalary()))
                .append("\n    ")
                .append(String.format(Locale.US, template, worker2.getName(), date, date, worker2.getSalary()))
                .append("\n")
                .append("</employees>\n");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJsonReport() {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2022, Calendar.JUNE, 21);
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Fedor", now, now, 200);
        store.add(worker);
        store.add(worker2);
        GeneratorReport generate = new JSONGenerator();
        Report engine = new ReportEngine(store, generate);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"Ivan\",")
                .append("\"hired\":{\"year\":2022,\"month\":5,\"dayOfMonth\":21,")
                .append("\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"fired\":{\"year\":2022,\"month\":5,\"dayOfMonth\":21,")
                .append("\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"salary\":100.0},")
                .append("{\"name\":\"Fedor\",")
                .append("\"hired\":{\"year\":2022,\"month\":5,\"dayOfMonth\":21,")
                .append("\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"fired\":{\"year\":2022,\"month\":5,\"dayOfMonth\":21,")
                .append("\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"salary\":200.0}]");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}