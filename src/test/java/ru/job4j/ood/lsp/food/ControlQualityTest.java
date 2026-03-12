package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ControlQualityTest {
    @Test
    public void whenDistributeFoodsThenEachProductGoesToOwnStore() {
        LocalDate now = LocalDate.of(2026, 3, 10);
        Store warehouse = new Warehouse(() -> now);
        Store shop = new Shop(() -> now);
        Store trash = new Trash(() -> now);
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        Food freshBread = new Bread("Bread", LocalDate.of(2026, 3, 26), LocalDate.of(2026, 3, 6), 100, 20);
        Food regularMilk = new Milk("Milk", LocalDate.of(2026, 3, 21), LocalDate.of(2026, 3, 1), 100, 20);
        Food discountedCheese = new Cheese("Cheese", LocalDate.of(2026, 3, 12), LocalDate.of(2026, 2, 20), 100, 20);
        Food expiredMilk = new Milk("Expired milk", LocalDate.of(2026, 3, 9), LocalDate.of(2026, 2, 10), 100, 20);

        controlQuality.distribute(List.of(freshBread, regularMilk, discountedCheese, expiredMilk));

        assertEquals(List.of(freshBread), warehouse.findAll());
        assertEquals(2, shop.findAll().size());
        assertTrue(shop.findAll().contains(regularMilk));
        assertTrue(shop.findAll().contains(discountedCheese));
        assertEquals(80, discountedCheese.getPrice());
        assertEquals(List.of(expiredMilk), trash.findAll());
    }

    @Test
    @DisplayName("Тест динамики: при смене даты и вызове resort() ")
    public void whenDateChangesAndResortThenProductMovesAcrossStores() {
        // продукт проходит путь Warehouse -> Shop (без скидки) -> Shop (со скидкой) -> Trash

        AtomicReference<LocalDate> currentDate = new AtomicReference<>(LocalDate.of(2026, 3, 1));
        Store warehouse = new Warehouse(currentDate::get);
        Store shop = new Shop(currentDate::get);
        Store trash = new Trash(currentDate::get);
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        Food milk = new Milk("Milk", LocalDate.of(2026, 3, 11), LocalDate.of(2026, 3, 1), 100, 20);

        controlQuality.distribute(milk);
        assertEquals(List.of(milk), warehouse.findAll());

        currentDate.set(LocalDate.of(2026, 3, 6));
        controlQuality.resort();
        assertEquals(List.of(milk), shop.findAll());
        assertEquals(100, milk.getPrice());

        currentDate.set(LocalDate.of(2026, 3, 9));
        controlQuality.resort();
        assertEquals(List.of(milk), shop.findAll());
        assertEquals(80, milk.getPrice());

        currentDate.set(LocalDate.of(2026, 3, 11));
        controlQuality.resort();
        assertEquals(List.of(milk), trash.findAll());
    }
}
