package ru.job4j.gc.cache;

import java.io.*;
import java.nio.file.Paths;

/**
 * Класс описывает реализацию абстрактного класса.
 * Программа эмулирует поведение данного кэша.
 * Считываем текстовые файлы из системы и выдаем текст при запросе имени файла.
 * Если в кэше файла нет, кэш должен загрузить себе данные.
 * По умолчанию в кэше нет ни одного файла. Текстовые файл должны лежать в одной директории.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Метод получает имя файла, читает его содержимое,
     * выводит все в одну строку.
     * Далее кладем эту строку вместе с ключом в кэш (мапу).
     *
     * @param key имя файла
     * @return содержимое файла в одну строку
     */
    @Override
    protected String load(String key) {
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader read = new BufferedReader(
                new FileReader(Paths.get(cachingDir, key).toString()))) {
            read.lines().forEach(rsl::append);
            put(key, rsl.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl.toString();
    }
}
