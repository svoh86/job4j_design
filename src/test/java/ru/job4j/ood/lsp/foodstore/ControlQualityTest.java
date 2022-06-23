package ru.job4j.ood.lsp.foodstore;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ControlQualityTest {
    LocalDate currentDate = LocalDate.now();
    Store warehouse = new Warehouse();
    Store shop = new Shop();
    Store trash = new Trash();
    List<Store> storeList = List.of(warehouse, shop, trash);
    List<Food> foods = new ArrayList<>();

    @Test
    public void whenWarehouse() {
        Food milk = new Milk(
                "Milk", currentDate.plusDays(10), currentDate.minusDays(1), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        foods.add(milk);
        foods.add(cheese);
        ControlQuality controlQuality = new ControlQuality(storeList);
        for (Food food : foods) {
            controlQuality.distribution(food);
        }
        assertEquals(warehouse.getAll(), List.of(milk));
    }

    @Test
    public void whenTrash() {
        Food milk = new Milk(
                "Milk", currentDate.plusDays(10), currentDate.minusDays(1), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        foods.add(milk);
        foods.add(cheese);
        ControlQuality controlQuality = new ControlQuality(storeList);
        for (Food food : foods) {
            controlQuality.distribution(food);
        }
        assertEquals(trash.getAll(), List.of(cheese));
    }

    @Test
    public void whenShop() {
        Food bread = new Bread(
                "Bread", currentDate.plusDays(3), currentDate.minusDays(3), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        foods.add(bread);
        foods.add(cheese);
        ControlQuality controlQuality = new ControlQuality(storeList);
        for (Food food : foods) {
            controlQuality.distribution(food);
        }
        assertEquals(shop.getAll(), List.of(bread));
    }

    @Test
    public void whenShopDiscount() {
        Food bread = new Bread(
                "Bread", currentDate.plusDays(1), currentDate.minusDays(5), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        foods.add(bread);
        foods.add(cheese);
        ControlQuality controlQuality = new ControlQuality(storeList);
        for (Food food : foods) {
            controlQuality.distribution(food);
        }
        assertThat(bread.getPrice(), is(90d));
    }
}