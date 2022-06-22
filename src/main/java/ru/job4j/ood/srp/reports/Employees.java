package ru.job4j.ood.srp.reports;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Класс-обертка, так как JAXB ждет аннотаций, а у коллекций их нет.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {
    @XmlElement(name = "employee")
    private List<Employee> employees;

    public Employees() {
    }

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
