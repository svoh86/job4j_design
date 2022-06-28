package ru.job4j.ood.dip;

import java.io.FileWriter;

/**
 * Метод write() нарушает принцип DIP, т.к. записывает данные в файл.
 * Лучше интерфейс и от него реализовать класс Writer.
 * В интерфейсе метод write() создать с параметрами, чтобы можно было сохранять не только в файл.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Writer {
    public void write(String path, String text) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
