package ru.job4j.algo.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Быстрая сортировка
 */
public class QuickList {

    public static <T> void quickSort(List<T> sequence, Comparator<T> comparator) {
        quickSort(sequence, 0, sequence.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> sequence, int start, int end, Comparator<T> comparator) {
        if (start >= end) {
            return;
        }
        int partition = breakPartition(sequence, start, end, comparator);
        quickSort(sequence, start, partition - 1, comparator);
        quickSort(sequence, partition + 1, end, comparator);
    }

    private static <T> int breakPartition(List<T> sequence, int start, int end, Comparator<T> comparator) {
        T pivot = sequence.get(end);
        int boundary = start;
        // Переносим элементы, которые должны стоять не правее опорного.
        for (int index = start; index < end; index++) {
            if (comparator.compare(sequence.get(index), pivot) <= 0) {
                swap(sequence, boundary, index);
                boundary++;
            }
        }
        // Ставим опорный элемент между двумя частями разбиения.
        swap(sequence, boundary, end);
        return boundary;
    }

    private static <T> void swap(List<T> array, int i, int j) {
        T tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    }
}
