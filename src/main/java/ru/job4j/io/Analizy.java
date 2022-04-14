package ru.job4j.io;

import java.io.*;

/**
 * Класс описывает преобразование одного файла в другой
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Analizy {
    /**
     * Метод находит диапазоны, когда сервер не работал.
     * Сервер не работал, если status = 400 или 500.
     * Диапазоном считается последовательность статусов 400 и 500.
     * Используем переменную startTime как маркер изменения статуса.
     *
     * @param source путь к файлу лога
     * @param target путь к файлу результата анализа
     */
    public void unavailable(String source, String target) {
        String startTime = null;
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] array = line.split("\\s");
                if (Integer.parseInt(array[0]) > 300 && startTime == null) {
                    startTime = array[1];
                } else if (Integer.parseInt(array[0]) <= 300 && startTime != null) {
                    out.println(startTime + ";" + array[1]);
                    startTime = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}
