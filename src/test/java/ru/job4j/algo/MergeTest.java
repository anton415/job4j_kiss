package ru.job4j.algo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MergeTest {
    @Test
    @DisplayName("Сортировка массива с помощью алгоритма слияния")
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertArrayEquals(new int[]{-13, 2, 3, 4, 4, 6, 8, 10}, Merge.mergesort(array));
    }

    @Test
    @DisplayName("Пустой массив остается пустым после сортировки")
    void whenArrayIsEmptyThenReturnEmptyArray() {
        int[] array = {};
        assertArrayEquals(new int[]{}, Merge.mergesort(array));
    }

    @Test
    @DisplayName("Массив из одного элемента остается без изменений")
    void whenArrayHasOneElementThenReturnSameArray() {
        int[] array = {7};
        assertArrayEquals(new int[]{7}, Merge.mergesort(array));
    }

    @Test
    @DisplayName("Уже отсортированный массив не изменяет порядок элементов")
    void whenArrayIsAlreadySortedThenReturnSortedArray() {
        int[] array = {-5, -1, 0, 3, 9};
        assertArrayEquals(new int[]{-5, -1, 0, 3, 9}, Merge.mergesort(array));
    }

    @Test
    @DisplayName("Массив в обратном порядке сортируется по возрастанию")
    void whenArrayIsReverseSortedThenReturnSortedArray() {
        int[] array = {9, 7, 5, 3, 1};
        assertArrayEquals(new int[]{1, 3, 5, 7, 9}, Merge.mergesort(array));
    }
}
