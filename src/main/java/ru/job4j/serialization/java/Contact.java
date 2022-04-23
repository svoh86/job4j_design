package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Класс описывает сериализацию и десериализацию.
 * Для стандартной сериализации объекта необходимо в классе имплементировать интерфейс Serializable,
 * этот интерфейс является маркерным, т.е. нет необходимости реализовывать его методы, он сообщает JVM,
 * что объект нашего класса может быть сериализован.
 * Для сериализации объектов в поток используется метод writeObject,
 * для чтения из потока readObject класса ObjectOutputStream.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, phone);
    }

    @Override
    public String toString() {
        return "Contact{" + "zipCode=" + zipCode
                + ", phone='" + phone + '\'' + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        /* Запись объекта во временный файл, который удалится системой */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
            System.out.println(contact.equals(contactFromFile));
        }
    }
}
