package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.Objects;

public class Food {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private final double originalPrice;
    private final double discount;
    private double price;
    private boolean discounted;
    // поле для сохранения значения оставшегося срока годности
    private long remainingShelfLife;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.expiryDate = Objects.requireNonNull(expiryDate, "expiryDate must not be null");
        this.createDate = Objects.requireNonNull(createDate, "createDate must not be null");
        if (!expiryDate.isAfter(createDate)) {
            throw new IllegalArgumentException("expiryDate must be after createDate");
        }
        if (price < 0) {
            throw new IllegalArgumentException("price must be non-negative");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("discount must be between 0 and 100");
        }
        this.originalPrice = price;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public long getRemainingShelfLife() {
        return remainingShelfLife;
    }

    public boolean isDiscounted() {
        return discounted;
    }

    void setRemainingShelfLife(long remainingShelfLife) {
        this.remainingShelfLife = Math.max(0, remainingShelfLife);
    }

    public void applyDiscount() {
        if (!discounted) {
            price = originalPrice * (1 - discount / 100D);
            discounted = true;
        }
    }
}
