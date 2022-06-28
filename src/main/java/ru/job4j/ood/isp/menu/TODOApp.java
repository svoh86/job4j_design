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

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        boolean run = true;
        while (run) {
            System.out.printf("%nВыберите действие:%n1.Создать задачу"
                              + "%n2.Посмотреть список задач"
                              + "%n3.Выйти их программы%n");
            String select = SCANNER.nextLine();
            if (Integer.parseInt(select) == 1) {
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
            } else if (Integer.parseInt(select) == 2) {
                printer.print(menu);
            } else if (Integer.parseInt(select) == 3) {
                run = false;
            } else {
                System.out.println("Ошибка выбора");
            }
        }
    }
}
