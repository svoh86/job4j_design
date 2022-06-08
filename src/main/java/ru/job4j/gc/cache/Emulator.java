package ru.job4j.gc.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        DirFileCache dirFileCache = new DirFileCache(filesFromDir());
        String fileName = fileName();
        boolean run = true;
        while (run) {
            System.out.printf("%nВыберите действие:%n1.Прочитать содержимое файла "
                              + "%n2.Занести содержимое файла в кэш и прочитать"
                              + "%n3.Выйти их программы%n");
            String select = SCANNER.nextLine();
            if (Integer.parseInt(select) == 1) {
                System.out.println(dirFileCache.load(fileName));
            } else if (Integer.parseInt(select) == 2) {
                System.out.println(dirFileCache.get(fileName));
            } else if (Integer.parseInt(select) == 3) {
                run = false;
            } else {
                System.out.println("Ошибка выбора");
            }
        }
    }

    /**
     * Метод считывает с консоли введенное пользователем имя директории
     * и выводит список файлов из неё.
     * Если такой директории нет, то выводит список файлов из корня проекта.
     *
     * @return имя директории
     * @throws IOException исключение
     */
    private static String filesFromDir() throws IOException {
        String defaultDir = ".";
        System.out.println("Укажите директорию: ");
        String dir = SCANNER.nextLine();
        if (!Files.exists(Path.of(dir))) {
            dir = defaultDir;
            System.out.println("Указанная директория не сущуствует. Показаны файлы из корня проекта.");
        }
        Files.list(Path.of(dir)).forEach(System.out::println);
        return dir;
    }

    private static String fileName() {
        System.out.println("Укажите имя файла: ");
        return SCANNER.nextLine();
    }
}
