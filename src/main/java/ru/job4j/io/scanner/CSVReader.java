package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Класс описывает чтение из .csv файла и вывод в консоль или файл.
 * Параметры:
 * path - путь к файлу.csv; delimiter - разделитель;
 * out  - приемник данных (консоль или файл); filter - фильтр по столбцам.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class CSVReader {
    /**
     * Метод реализуют логику класса.
     * Запускаем scanner с делителем в виде новой строки.
     * Обрабатываем первую строку файла. Получаем массив строк через scanner.next().split().
     * Получаем лист фильтров и лист их индексов. Собираем первую строку.
     * Проверяем параметр out - выводим на консоль или в файл.
     *
     * @param argsName объект argsName
     */
    public static void handle(ArgsName argsName) {
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(System.lineSeparator())) {
            String[] lineData = scanner.next().split(argsName.get("delimiter"));
            List<String> filters = filters(argsName.get("filter"));
            List<Integer> indexFilters = getIndexOfFilters(lineData, filters);
            String first = "";
            for (String s : filters) {
                first = first.concat(first.isBlank() ? "" : ";") + s;
            }
            if (argsName.get("out").equals("stdout")) {
                System.out.println(first);
                while (scanner.hasNext()) {
                    String temp = getString(argsName, scanner, indexFilters);
                    System.out.println(temp);
                }
            } else {
                try (PrintWriter pw = new PrintWriter(new FileWriter(argsName.get("out")))) {
                    pw.println(first);
                    while (scanner.hasNext()) {
                        String temp = getString(argsName, scanner, indexFilters);
                        pw.println(temp);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод принимает строку параметров filter,
     * делит через split() и записывает в лист.
     *
     * @param filter параметр
     * @return лист параметров
     */
    private static List<String> filters(String filter) {
        String[] line = filter.split(",");
        return Arrays.asList(line);
    }

    /**
     * Метод принимает массив строк и лист параметров filter.
     * Циклом идем по массиву строк. Если в листе параметров есть строка,
     * то записываем её индекс в лист индексов.
     *
     * @param lineData массив строк
     * @param filters  лист параметров filter
     * @return лист индексов
     */
    private static List<Integer> getIndexOfFilters(String[] lineData, List<String> filters) {
        List<Integer> indexFilters = new ArrayList<>();
        for (int i = 0; i < lineData.length; i++) {
            if (filters.contains(lineData[i])) {
                indexFilters.add(i);
            }
        }
        return indexFilters;
    }

    /**
     * Метод собирает строку.
     * Создаем пустую строку.
     * Получаем массив строк через scanner и split().
     * Циклом идем по листу индексов фильтров.
     * Если строка непустая, то добавляем ; и строку из массива строк.
     *
     * @param argsName     объект argsName
     * @param scanner      объект scanner
     * @param indexFilters лист индексов фильтров
     * @return строку
     */
    private static String getString(ArgsName argsName, Scanner scanner, List<Integer> indexFilters) {
        String temp = "";
        String[] lines = scanner.next().split(argsName.get("delimiter"));
        for (Integer index : indexFilters) {
            temp = temp.concat(temp.isBlank() ? "" : ";") + lines[index];
        }
        return temp;
    }

    private static void check(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Enter path, delimiter, out and filter");
        }
        Path path = Paths.get(argsName.get("path"));
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("File don't exist " + path.toFile().getAbsoluteFile());
        }
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Delimiter must be \";\"");
        }
    }

    public static void main(String[] args) {
        check(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
