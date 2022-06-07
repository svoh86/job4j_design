package ru.job4j.gc.cache;

import java.util.Scanner;

/**
 * Класс для работы с пользователем.
 * Предоставить пользователю возможности:
 * указать кэшируемую директорию;
 * загрузить содержимое файла в кэш;
 * получить содержимое файла из кэша.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите директорию: ");
        String dir = scanner.nextLine();
        DirFileCache dirFileCache = new DirFileCache(dir);
        System.out.println("Укажите имя файла: ");
        String fileName = scanner.nextLine();
        dirFileCache.load(fileName);
        System.out.println(dirFileCache.get(fileName));
    }
}
