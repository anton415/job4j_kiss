package ru.job4j.algo;

import java.util.Arrays;

/**
 * Временная сложность: O(n log n), где n — количество элементов массива.
 * Память: O(n) на временные массивы и O(log n) на стек рекурсии.
 */
public class Merge {
    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            // Рекурсивно сортируем левую и правую половины массива.
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            // Сливаем две отсортированные половины в один массив.
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        // Сравниваем текущие элементы двух массивов и берём меньший.
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }

        // Дописываем остаток левого массива, если он ещё не закончился.
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }

        // Дописываем остаток правого массива, если он ещё не закончился.
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }

        return result;
    }
}
