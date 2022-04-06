package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс описывает запись в файл
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Matrix {
    public static void multiple(int size) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    String rsl = i * j + "\t";
                    out.write(rsl.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multiple(10);
    }
}
