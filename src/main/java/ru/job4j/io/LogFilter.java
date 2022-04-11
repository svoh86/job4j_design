package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает выбор строки, содержащей условие.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class LogFilter {
    /**
     * Метод проверяет условие, что предпоследнее значение в строке == 404.
     * Создаем ArrayList. Открываем буферизированный поток.
     * В цикле разбиваем каждую строку на массив строк через split("\s").
     * Если предпоследнее значение массива == 404,
     * то записываем всю строку line в ArrayList.
     *
     * @param file строка
     * @return лист строк
     */
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] strings = (line.split("\s"));
                if ("404".equals(strings[strings.length - 2])) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
