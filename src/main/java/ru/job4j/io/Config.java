package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Класс описывает чтение из файла настроек,
 * который содержит пары ключ-значение.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод считывает все ключи в карту.
     * Проверяем, что строка не начинаеися с # (комментарий) и не пустая.
     * Тогда делим строку через split("="). Если длина массива <2,
     * то выбрасывем мсключение IllegalArgumentException.
     * Иначе кладем первую и вторую ячейки массива в карту.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] array = line.split("=", 2);
                    if (array.length < 2 || array[0].isEmpty() || array[1].isEmpty()) {
                        throw new IllegalArgumentException("key=value pattern violation");
                    }
                    values.put(array[0], array[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app.properties"));
    }
}
