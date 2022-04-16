package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс описывает архивацию папки
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Zip {
    /**
     * Метод архивирует папки и файлы.
     * Для архивации используем классы ZipOutputStream.
     *
     * @param sources список путей файлов, которые надо архивировать
     * @param target  во что архивировать
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Точка входа программы.
     * Нужно прописать параметры запуска main метода.
     * Для работы с входными аргументами используем класс ArgsName.
     * Для поиска и фильтрации файлов используем класс Search.
     *
     * @param args параметры запуска
     * @throws IOException исключения
     */
    public static void main(String[] args) throws IOException {
        /**
         Zip zipSingle = new Zip();
         zipSingle.packSingleFile(new File("./pom.xml"), new File("./pom.zip"));
         */
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        List<Path> paths = Search.search(Paths.get(argsName.get("d")),
                p -> !p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(paths, Paths.get(argsName.get("o")).toFile());
    }
}
