package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс описывает разбиение параметров на ключ-пара
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Wrong argument");
        }
        return values.get(key);
    }

    /**
     * Метод разбивает массив аргументов через split(),
     * выполняется проверка на соответствие шаблону через check(),
     * заносится в мапу с удалением символа "-".
     *
     * @param args аргументы
     */
    private void parse(String[] args) {
        ArgsName name = new ArgsName();
        for (String arg : args) {
            String[] line = arg.split("=", 2);
            name.check(line);
            values.put(line[0].replaceAll("-", ""), line[1].replaceAll("-", ""));
        }
    }

    /**
     * Метод проверяет строки на соответствие шаблону key=value
     *
     * @param args аргументы
     */
    private void check(String[] args) {
        if (args.length < 2 || args[0].isEmpty() || args[1].isEmpty()) {
            throw new IllegalArgumentException("key=value pattern violation");
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
