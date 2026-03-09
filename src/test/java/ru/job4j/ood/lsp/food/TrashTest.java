package ru.job4j.ood.lsp.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TrashTest {
    @Test
    public void whenShelfLifeExpiredThenStoreInTrash() {
        LocalDate now = LocalDate.of(2026, 3, 10);
        Store trash = new Trash(() -> now);
        Food milk = new Milk("Milk", LocalDate.of(2026, 3, 9), LocalDate.of(2026, 2, 10), 100, 20);

        boolean added = trash.add(milk);

        assertTrue(added);
        assertEquals(1, trash.findAll().size());
        assertTrue(trash.findAll().contains(milk));
    }
}
