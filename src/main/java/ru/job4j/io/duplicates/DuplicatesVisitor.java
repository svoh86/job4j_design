package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Класс описывает поиск дубликатов файлов и вывод их в консоль
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> map = new HashMap<>();

    /**
     * Метод ищет дубликаты файлов.
     * Создаем объект FileProperty.
     * Добавляем в мапу объект типа FileProperty, если его там нет,
     * и пустой ArrayList.
     * Получаем ArrayList по ключу (FileProperty) и добавляем в лист путь файла.
     * Если будут дубликаты, то длина ArrayList будет больше 1.
     *
     * @param file  файл
     * @param attrs атрибут
     * @return продолжаем обход дерева
     * @throws IOException исключение
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        map.putIfAbsent(fileProperty, new ArrayList<>());
        map.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    /**
     * Метод печатает абсоютные пути всех дубликатов
     */
    public void printDouble() {
        map.values().stream()
                .filter(l -> l.size() > 1)
                .flatMap(List::stream)
                .forEach(p -> System.out.println("Double file: " + p.toAbsolutePath()));
    }
}
