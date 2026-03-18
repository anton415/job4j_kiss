package ru.job4j.algo;

import java.util.ArrayList;
import static java.util.Arrays.sort;

/**
 * Временная сложность: O(n log n), где n — количество интервалов.
 * Дополнительная память: O(n).
 */
public class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }
        sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[] current = intervals[0];
        ArrayList<int[]> merged = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (current[1] >= next[0]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);
        return merged.toArray(new int[0][]);    
    }
}
