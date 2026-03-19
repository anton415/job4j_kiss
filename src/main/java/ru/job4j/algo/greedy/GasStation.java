package ru.job4j.algo.greedy;

public class GasStation {
    /**
     * Газовая станция: проверка жадного алгоритма
     * Жадный проход по кругу: переносим старт, когда текущий баланс уходит в минус.
     * Сложность: время O(n), память O(1).
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // Некорректный или пустой ввод не позволяет выбрать старт.
        if (gas == null || cost == null || gas.length == 0 || gas.length != cost.length) {
            return -1;
        }

        int totalBalance = 0;
        int currentBalance = 0;
        int startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            int balance = gas[i] - cost[i];
            totalBalance += balance;
            currentBalance += balance;

            // Если до текущей позиции доехать нельзя, начинаем после нее.
            if (currentBalance < 0) {
                startIndex = i + 1;
                currentBalance = 0;
            }
        }

        // Решение существует только при неотрицательном общем балансе.
        return totalBalance >= 0 ? startIndex : -1;
    }
}
