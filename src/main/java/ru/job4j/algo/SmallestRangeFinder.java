package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        // если входные данные некорректные, возвращаем null
        if (nums == null || nums.length == 0 || k <= 0) {
            return null;
        }

        // два указателя задают текущий рассматриваемый диапазон [left, right]
        int left = 0;

        // изначальные границы лучшего найденного диапазона
        int bestLeft = 0;
        int bestRight = 0;

        // диапазон найден?
        boolean found = false;

        // количество чисел находится внутри текущего диапазона.
        int distinctCount = 0;

        for (int right = 0; right < nums.length; right++) {
            // В отсортированном массиве новое различное значение начинается
            // там, где текущий элемент отличается от предыдущего.
            if (right == 0 || nums[right] != nums[right - 1]) {
                distinctCount++;
            }

            // Пока диапазон содержит хотя бы k различных значений,
            // пробуем сузить его слева и обновить лучший ответ.
            while (distinctCount >= k) {
                int currentLength = right - left;
                int bestLength = bestRight - bestLeft;

                // Первый валидный диапазон сохраняем без сравнения.
                // Дальше обновляем ответ, только если нашли диапазон короче.
                if (!found || currentLength < bestLength) {
                    bestLeft = left;
                    bestRight = right;
                    found = true;
                }

                // distinctCount уменьшается, когда из диапазона уходит последнее значение left
                if (left == right || nums[left] != nums[left + 1]) {
                    distinctCount--;
                }
                left++;
            }
        }

        if (!found) {
            return null;
        }

        return new int[]{bestLeft, bestRight};
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
