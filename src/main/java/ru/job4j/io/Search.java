package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс описывает поиск файлов в директориях
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Enter path and extension");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Метод ищет файлы в директории, проходя по древовидной структуре файловой системы.
     * Через класс SearchFiles, который имплементит FileVisitor,
     * и его методы visitFile и getPaths() получаем список с путями, которые соответствуют условию.
     *
     * @param root      директория, где будем искать
     * @param condition условие поиска
     * @return лист путей
     * @throws IOException исключение
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
