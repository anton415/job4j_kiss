package ru.job4j.ood.lsp.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class WarehouseTest {
    @Test
    public void whenShelfLifeUsedLessThanTwentyFivePercentThenStoreInWarehouse() {
        LocalDate now = LocalDate.of(2026, 3, 10);
        Store warehouse = new Warehouse(() -> now);
        Food bread = new Bread("Bread", LocalDate.of(2026, 3, 26), LocalDate.of(2026, 3, 6), 100, 20);

        boolean added = warehouse.add(bread);

        assertTrue(added);
        assertEquals(1, warehouse.findAll().size());
        assertTrue(warehouse.findAll().contains(bread));
    }
}
