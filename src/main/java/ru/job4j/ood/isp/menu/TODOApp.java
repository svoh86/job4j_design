package ru.job4j.ood.isp.menu;

import java.util.Scanner;

/**
 * Класс служит для построения и вывода списка задач пользователя.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class TODOApp {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final ActionDelegate STUB_ACTION = null;
    private static final int CREATE_TASK = 1;
    private static final int VIEW_LIST_OF_TASKS = 2;
    private static final int EXIT = 3;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        boolean run = true;
        while (run) {
            System.out.print("""
                    Выберите действие:
                    1.Создать задачу
                    2.Посмотреть список задач
                    3.Выйти их программы
                    """);
            String select = SCANNER.nextLine();
            if (Integer.parseInt(select) == CREATE_TASK) {
                System.out.printf("%nВводим задачу(1) или подзадачу?(2): 1 / 2%n");
                select = SCANNER.nextLine();
                if (Integer.parseInt(select) == 1) {
                    System.out.printf("%nВведите название задачи:%n");
                    select = SCANNER.nextLine();
                    menu.add(Menu.ROOT, select, STUB_ACTION);
                } else if (Integer.parseInt(select) == 2) {
                    System.out.printf("%nВведите название задачи, к которой относится подзадача:%n");
                    select = SCANNER.nextLine();
                    System.out.printf("%nВведите название подзадачи:%n");
                    String anotherSelect = SCANNER.nextLine();
                    menu.add(select, anotherSelect, STUB_ACTION);
                } else {
                    System.out.println("Ошибка выбора");
                }
            } else if (Integer.parseInt(select) == VIEW_LIST_OF_TASKS) {
                printer.print(menu);
            } else if (Integer.parseInt(select) == EXIT) {
                run = false;
            } else {
                System.out.println("Ошибка выбора");
            }
        }
    }
}
