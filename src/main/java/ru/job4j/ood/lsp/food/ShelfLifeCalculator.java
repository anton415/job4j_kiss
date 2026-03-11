package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Расчет срока годности
 */
public class ShelfLifeCalculator {
    public double usagePercent(Food food, LocalDate currentDate) {
        Objects.requireNonNull(food, "food must not be null");
        updateRemainingShelfLife(food, currentDate);
        return usagePercent(food.getCreateDate(), food.getExpiryDate(), currentDate);
    }

    public double usagePercent(LocalDate createDate, LocalDate expiryDate, LocalDate currentDate) {
        Objects.requireNonNull(createDate, "createDate must not be null");
        Objects.requireNonNull(expiryDate, "expiryDate must not be null");
        Objects.requireNonNull(currentDate, "currentDate must not be null");
        if (currentDate.isBefore(createDate)) {
            return 0D;
        }
        long totalDays = ChronoUnit.DAYS.between(createDate, expiryDate);
        long spentDays = ChronoUnit.DAYS.between(createDate, currentDate);
        return spentDays * 100D / totalDays;
    }

    public boolean isExpired(Food food, LocalDate currentDate) {
        Objects.requireNonNull(food, "food must not be null");
        updateRemainingShelfLife(food, currentDate);
        return isExpired(food.getExpiryDate(), currentDate);
    }

    public boolean isExpired(LocalDate expiryDate, LocalDate currentDate) {
        Objects.requireNonNull(expiryDate, "expiryDate must not be null");
        Objects.requireNonNull(currentDate, "currentDate must not be null");
        return !currentDate.isBefore(expiryDate);
    }

    public boolean needsDiscount(Food food, LocalDate currentDate) {
        Objects.requireNonNull(food, "food must not be null");
        updateRemainingShelfLife(food, currentDate);
        return needsDiscount(food.getCreateDate(), food.getExpiryDate(), currentDate);
    }

    public boolean needsDiscount(LocalDate createDate, LocalDate expiryDate, LocalDate currentDate) {
        double used = usagePercent(createDate, expiryDate, currentDate);
        return used > 75D && !isExpired(expiryDate, currentDate);
    }

    private void updateRemainingShelfLife(Food food, LocalDate currentDate) {
        food.setRemainingShelfLife(ChronoUnit.DAYS.between(currentDate, food.getExpiryDate()));
    }
}
