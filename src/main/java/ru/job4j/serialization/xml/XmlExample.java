package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Класс описывает преобразование объекта в XML и обратно.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@XmlRootElement(name = "xmlexample")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlExample {
    @XmlAttribute
    private boolean permission;
    @XmlAttribute
    private int experience;
    @XmlAttribute
    private String passport;
    private User user;
    @XmlElementWrapper(name = "children")
    @XmlElement(name = "child")
    private Integer[] children;

    public XmlExample() {
    }

    public XmlExample(boolean permission, int experience, String passport, User user, Integer[] children) {
        this.permission = permission;
        this.experience = experience;
        this.passport = passport;
        this.user = user;
        this.children = children;
    }

    @Override
    public String toString() {
        return "JsonExample{" + "permission=" + permission
                + ", experience=" + experience + ", passport='"
                + passport + '\'' + ", user=" + user + ", children="
                + Arrays.toString(children) + '}';
    }

    /**
     * создаем объект XmlExample.
     * Получаем контекст для доступа к АПИ через JAXBContext.newInstance(XmlExample.class).
     * Создаем сериализатор через context.createMarshaller().
     * Указываем, что нам нужно форматирование.
     * Сериализуем через marshaller.marshal(xmlExample, writer).
     * Для десериализации нам нужно создать десериализатор.
     * Десериализуем через (XmlExample) unmarshaller.unmarshal(reader).
     *
     * @param args параметры
     */
    public static void main(String[] args) throws Exception {
        XmlExample xmlExample = new XmlExample(true, 15, "111-1111",
                new User("Ivan", "Ivanov"), new Integer[]{10, 6});
        JAXBContext context = JAXBContext.newInstance(XmlExample.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(xmlExample, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            XmlExample rsl = (XmlExample) unmarshaller.unmarshal(reader);
            System.out.println(rsl);
        }
    }
}
