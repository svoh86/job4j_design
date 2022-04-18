package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Класс описывает работу бота с ответами, которые читаются readPhrases() рандомно из файла.
 * Весь чат записывается в лог - saveLog().
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> answers;
    private List<String> records = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод запускает бота. Все строки записываем в лист records.
     * Юзер вводит строку. Запускаем цикл.
     * Для проверки условий "стоп" и "продолжить" вводим boolean flag = true;
     */
    public void run() {
        String ls = System.lineSeparator();
        System.out.println("Привет. Я - бот." + ls
                + "Чтобы я замолчал - напиши \"стоп\"." + ls
                + "Чтобы я снова заговорил - напиши \"продолжить\"." + ls
                + "Для выхода - напиши \"закончить\"." + ls
                + "Наш разговор записывается.");
        readPhrases();
        Scanner input = new Scanner(System.in);
        String userPhrase = input.nextLine();
        records.add(userPhrase);
        boolean flag = true;
        while (!OUT.equals(userPhrase)) {
            if (STOP.equals(userPhrase)) {
                flag = false;
            }
            if (CONTINUE.equals(userPhrase)) {
                flag = true;
            }
            if (flag) {
                Collections.shuffle(answers);
                String bot = answers.get(0);
                System.out.println(bot);
                records.add(bot);
            }
            userPhrase = input.nextLine();
            records.add(userPhrase);
        }
        saveLog(records);
    }

    /**
     * Метод читает строки из файла и собирает их в лист answers.
     */
    private void readPhrases() {
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            answers = in.lines().map(s -> s + System.lineSeparator()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод записывает лист переписки в файл
     *
     * @param log лист переписки
     */
    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/logPhrases.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
