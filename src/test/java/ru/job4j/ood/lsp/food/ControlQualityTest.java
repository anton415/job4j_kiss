package ru.job4j.ood.lsp.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

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
}
