package ru.job4j.ood.isp.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."),
                menu.select("Сходить в магазин").get());
        assertEquals(new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."),
                menu.select("Купить продукты").get());
        assertEquals(new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."),
                menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenAddThenSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        assertEquals(menu.select("Купить продукты").get(),
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб"), STUB_ACTION, "1.1."));
    }

    /**
     * Создаём поток для сохранения выводимых данных - baos.
     * Заменяем поток вывода на наш буфер: System.setOut(new PrintStream(baos));
     *
     * @throws IOException исключение
     */
    @Test
    public void whenAddThenPrint() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        MenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);
        String ln = System.lineSeparator();
        StringBuilder str = new StringBuilder();
        str.append("Сходить в магазин 1.")
                .append(ln)
                .append("----Купить продукты 1.1.")
                .append(ln)
                .append("--------Купить хлеб 1.1.1.")
                .append(ln)
                .append("Покормить собаку 2.")
                .append(ln);
        assertEquals(str.toString(), baos.toString());
        baos.close();
    }
}