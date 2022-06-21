package ru.job4j.ood.srp.reports.generator;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

/**
 * Класс генерирует отчет в формате xml.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class XmlGenerator implements GeneratorReport {
    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            for (Employee employee : store.findBy(filter)) {
                marshaller.marshal(employee, writer);
                xml = (writer.getBuffer().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
