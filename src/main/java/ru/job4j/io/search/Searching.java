package ru.job4j.io.search;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Класс описывает поиск файлов в директориях по критерию:
 * по имени, маске или регулярному выражению.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Searching {
    private static List<Path> paths;

    public static void main(String[] args) throws IOException {
        check(args);
        Path start = Paths.get(args[0]);
        try (FileWriter fw = new FileWriter(args[3])) {
            if ("name".equals(args[2])) {
                paths = searchByName(start, args);
            }
            if ("mask".equals(args[2])) {
                paths = searchByMask(start, args);
            }
            if ("regex".equals(args[2])) {
                paths = searchByRegex(start, args);
            }
            for (Path p : paths) {
                String ls = System.lineSeparator();
                fw.write(p.toString() + ls);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void check(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Enter path, file name or mask or regex, search type and log file");
        }
        Path path = Paths.get(args[0]);
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory don't exist " + path.toFile().getAbsoluteFile());
        }
        if (!"name".equals(args[2]) && !"mask".equals(args[2]) && !"regex".equals(args[2])) {
            throw new IllegalArgumentException("Wrong search type argument");
        }
    }

    /**
     * Метод ищет файлы в директории, проходя по древовидной структуре файловой системы.
     * Поиск идет по имени файла (точное совпадение)
     *
     * @param root директория, в которой начинать поиск
     * @param args параметры
     * @return лист путей
     * @throws IOException исключение
     */
    private static List<Path> searchByName(Path root, String[] args) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().equals(args[1]));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * Метод ищет файлы в директории, проходя по древовидной структуре файловой системы.
     * Поиск идет по маске
     *
     * @param root директория, в которой начинать поиск
     * @param args параметры
     * @return лист путей
     * @throws IOException исключение
     */
    private static List<Path> searchByMask(Path root, String[] args) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName()
                .matches("^" + (args[1]
                        .replace("?", ".")
                        .replace("*", ".*")) + "$"));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * Метод ищет файлы в директории, проходя по древовидной структуре файловой системы.
     * Поиск идет по регулярному выражению
     *
     * @param root директория, в которой начинать поиск
     * @param args параметры
     * @return лист путей
     * @throws IOException исключение
     */
    private static List<Path> searchByRegex(Path root, String[] args) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName()
                .matches(args[1]));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
