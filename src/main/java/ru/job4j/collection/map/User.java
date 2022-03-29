package ru.job4j.collection.map;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.hash;

/**
 * Класс описывает модель данных Пользователя
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class User {
    private String name;
    private int children;
    Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User first = new User("Ivan", 1, new GregorianCalendar(2020, 0, 10));
        User second = new User("Ivan", 1, new GregorianCalendar(2020, 0, 10));
        Map<User, Object> userMap = new HashMap<>();
        userMap.put(first, new Object());
        userMap.put(second, new Object());
        for (User key : userMap.keySet()) {
            Object value = userMap.get(key);
            System.out.println(key + " " + value);
        }
        int hk1 = first.hashCode();
        int hs1 = hash(hk1);
        int index1 = hs1 & (16 - 1);
        int hk2 = second.hashCode();
        int hs2 = hash(hk2);
        int index2 = hs2 & (16 - 1);
        System.out.println(hs1 + " " + hk1 + " " + index1);
        System.out.println(hs2 + " " + hk2 + " " + index2);
        System.out.println("KING".hashCode());
        System.out.println(hash("KING".hashCode()));
    }
}