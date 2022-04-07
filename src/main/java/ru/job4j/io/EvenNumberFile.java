package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Класс описывает чтение из файла
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class EvenNumberFile {
    /**
     * Считываем каждый байт из файла в rsl пока метод
     * in.read() не вернет -1 (пока есть данные).
     * Считанные байт приводим к char и записывает в text.
     * Через метод split() переводим в массив строк и
     * после приведения к int проверяем на четность.
     *
     * @param args аргумент
     */
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int rsl;
            while ((rsl = in.read()) != -1) {
                text.append((char) rsl);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line + " четное");
                } else {
                    System.out.println(line + " нечетное");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
