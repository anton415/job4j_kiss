package ru.job4j.algo.bank;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankMaxLoadTimeTest {

    @Test
    @DisplayName("Простой случай: четыре клиента дают пик с 4 до 5")
    void whenSimpleCaseThenCorrectMaxLoadTime() {
        List<int[]> visitTimes = Arrays.asList(
            new int[]{1, 5},
            new int[]{2, 6},
            new int[]{3, 8},
            new int[]{4, 7}
        );

        int[] result = BankMaxLoadTime.findMaxLoadTime(visitTimes);
        assertArrayEquals(new int[]{4, 5}, result);
    }

    @Test
    @DisplayName("Перекрытия разной длины: максимум на отрезке с 5 до 6")
    void whenOverlapCaseThenCorrectMaxLoadTime() {
        List<int[]> visitTimes = Arrays.asList(
            new int[]{1, 10},
            new int[]{2, 6},
            new int[]{5, 8},
            new int[]{7, 12}
        );

        int[] result = BankMaxLoadTime.findMaxLoadTime(visitTimes);
        assertArrayEquals(new int[]{5, 6}, result);
    }

    @Test
    @DisplayName("Без перекрытий: первый интервал остается максимальным")
    void whenNoOverlapCaseThenCorrectMaxLoadTime() {
        List<int[]> visitTimes = Arrays.asList(
            new int[]{1, 2},
            new int[]{3, 4},
            new int[]{5, 6}
        );

        int[] result = BankMaxLoadTime.findMaxLoadTime(visitTimes);
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    @DisplayName("Сложный случай: максимум достигается только в момент времени 3")
    void whenComplexCaseThenCorrectMaxLoadTime() {
        List<int[]> visitTimes = Arrays.asList(
            new int[]{1, 3},
            new int[]{2, 4},
            new int[]{3, 5},
            new int[]{4, 6},
            new int[]{5, 7},
            new int[]{6, 8}
        );

        int[] result = BankMaxLoadTime.findMaxLoadTime(visitTimes);
        assertArrayEquals(new int[]{3, 3}, result);
    }
}
