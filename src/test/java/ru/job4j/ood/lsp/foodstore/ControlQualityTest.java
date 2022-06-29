package ru.job4j.ood.lsp.foodstore;

import org.junit.Test;

import java.time.LocalDate;
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

    @Test
    public void whenWarehouse() {
        Food milk = new Milk(
                "Milk", currentDate.plusDays(10), currentDate.minusDays(1), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.distribution(milk);
        controlQuality.distribution(cheese);
        assertEquals(warehouse.getAll(), List.of(milk));
    }

    @Test
    public void whenTrash() {
        Food milk = new Milk(
                "Milk", currentDate.plusDays(10), currentDate.minusDays(1), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.distribution(milk);
        controlQuality.distribution(cheese);
        assertEquals(trash.getAll(), List.of(cheese));
    }

    @Test
    public void whenShop() {
        Food bread = new Bread(
                "Bread", currentDate.plusDays(3), currentDate.minusDays(3), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.distribution(bread);
        controlQuality.distribution(cheese);
        assertEquals(shop.getAll(), List.of(bread));
    }

    @Test
    public void whenShopDiscount() {
        Food bread = new Bread(
                "Bread", currentDate.plusDays(1), currentDate.minusDays(5), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.distribution(bread);
        controlQuality.distribution(cheese);
        assertThat(bread.getPrice(), is(90d));
    }

    @Test
    public void whenAllConditions() {
        Food milk = new Milk(
                "Milk", currentDate.plusDays(10), currentDate.minusDays(1), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        Food bread = new Bread(
                "Bread", currentDate.plusDays(3), currentDate.minusDays(3), 100, 10);
        Food breadDiscount = new Bread(
                "Bread", currentDate.plusDays(1), currentDate.minusDays(5), 100, 10);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.distribution(milk);
        controlQuality.distribution(cheese);
        controlQuality.distribution(bread);
        controlQuality.distribution(breadDiscount);
        assertEquals(warehouse.getAll(), List.of(milk));
        assertEquals(trash.getAll(), List.of(cheese));
        assertEquals(shop.getAll(), List.of(bread, breadDiscount));
    }

    @Test
    public void whenResort() {
        Food milk = new Milk(
                "Milk", currentDate.plusDays(10), currentDate.minusDays(1), 100, 10);
        Food cheese = new Cheese(
                "Cheese", currentDate.minusDays(10), currentDate.minusDays(20), 200, 20);
        Food bread = new Bread(
                "Bread", currentDate.plusDays(3), currentDate.minusDays(3), 100, 10);
        Food breadDiscount = new Bread(
                "Bread", currentDate.plusDays(1), currentDate.minusDays(5), 100, 10);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.distribution(milk);
        controlQuality.distribution(cheese);
        controlQuality.distribution(bread);
        controlQuality.distribution(breadDiscount);
        cheese.setExpiryDate(currentDate.plusDays(10));
        cheese.setCreateDate(currentDate.minusDays(1));
        controlQuality.resort();
        assertEquals(warehouse.getAll(), List.of(milk, cheese));
        assertEquals(trash.getAll(), List.of());
        assertEquals(shop.getAll(), List.of(bread, breadDiscount));
    }
}