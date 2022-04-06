package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс описывает сравнение двух множеств
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Analize {
    /**
     * Метод считает кол-во добавлений, замен и удалений
     *
     * @param previous начальное множество
     * @param current  текущее множество
     * @return объект типа Info
     * Переводим множество previous в Map.
     * Циклом идем по множеству current.
     * Если в мапе нет такого ключа, тогда added++.
     * Если в мапе есть такой ключ, но имена User разные, тогда changed++.
     * Кол-во удаленных получаем из разницы размеров множеств + кол-во добавленных.
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        int added = 0;
        int changed = 0;
        for (User user : current) {
            if (!map.containsKey(user.getId())) {
                added++;
            }
            if (map.containsKey(user.getId()) && !map.get(user.getId()).equals(user.getName())) {
                changed++;
            }
        }
        int deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }
}
