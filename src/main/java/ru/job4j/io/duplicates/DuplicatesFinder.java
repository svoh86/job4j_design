package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс - точка вода программы поиска дубликатов файлов.
 * Дубликат - если одинаковые имена и размер файлов
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Path start = Paths.get(".");
        Files.walkFileTree(start, duplicatesVisitor);
        duplicatesVisitor.printDouble();
    }
}
