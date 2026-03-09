package ru.job4j.ood.lsp.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ShopTest {
    @Test
    public void whenShelfLifeUsedBetweenTwentyFiveAndSeventyFivePercentThenStoreInShopWithoutDiscount() {
        LocalDate now = LocalDate.of(2026, 3, 10);
        Store shop = new Shop(() -> now);
        Food milk = new Milk("Milk", LocalDate.of(2026, 3, 21), LocalDate.of(2026, 3, 1), 100, 20);

        boolean added = shop.add(milk);

        assertTrue(added);
        assertEquals(100, milk.getPrice());
        assertFalse(milk.isDiscounted());
        assertTrue(shop.findAll().contains(milk));
    }

    @Test
    public void whenShelfLifeUsedMoreThanSeventyFivePercentThenStoreInShopWithDiscount() {
        LocalDate now = LocalDate.of(2026, 3, 10);
        Store shop = new Shop(() -> now);
        Food cheese = new Cheese("Cheese", LocalDate.of(2026, 3, 12), LocalDate.of(2026, 2, 20), 100, 20);

        boolean added = shop.add(cheese);

        assertTrue(added);
        assertEquals(80, cheese.getPrice());
        assertTrue(cheese.isDiscounted());
        assertTrue(shop.findAll().contains(cheese));
    }
}
