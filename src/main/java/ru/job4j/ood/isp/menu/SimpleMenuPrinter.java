package ru.job4j.ood.isp.menu;

/**
 * Класс печатает меню в консоль с отступами
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SimpleMenuPrinter implements MenuPrinter {
    private static final String SPACE = "----";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int count = menuItemInfo.getNumber().split("\\.").length - 1;
            System.out.printf("%s%s %s%n", SPACE.repeat(count), menuItemInfo.getName(), menuItemInfo.getNumber());
        }
    }
}
