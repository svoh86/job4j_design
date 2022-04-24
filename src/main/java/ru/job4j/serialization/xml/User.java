package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@XmlRootElement(name = "user")
public class User {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String surname;

    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", surname='" + surname + '\'' + '}';
    }
}
