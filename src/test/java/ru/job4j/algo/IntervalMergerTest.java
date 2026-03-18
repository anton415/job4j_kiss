package ru.job4j.algo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class IntervalMergerTest {
        @Test
    void whenIntervalsHaveOverlapsThenMergeOverlappingIntervals() {
        IntervalMerger intervalMerger = new IntervalMerger();
        int[][] intervals = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] expected = new int[][] {{1, 6}, {8, 10}, {15, 18}};
        int[][] result = intervalMerger.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    void whenIntervalsTouchAtEdgesThenMergeIntervals() {
        IntervalMerger intervalMerger = new IntervalMerger();
        int[][] intervals = new int[][] {{1, 4}, {4, 5}};
        int[][] expected = new int[][] {{1, 5}};
        int[][] result = intervalMerger.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    void whenIntervalsAreNonOverlappingThenReturnSameIntervals() {
        IntervalMerger intervalMerger = new IntervalMerger();
        int[][] intervals = new int[][] {{1, 2}, {3, 4}, {5, 6}};
        int[][] expected = new int[][] {{1, 2}, {3, 4}, {5, 6}};
        int[][] result = intervalMerger.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    void whenIntervalsFullyOverlapThenMergeIntoOneInterval() {
        IntervalMerger intervalMerger = new IntervalMerger();
        int[][] intervals = new int[][] {{1, 5}, {2, 6}, {3, 7}};
        int[][] expected = new int[][] {{1, 7}};
        int[][] result = intervalMerger.merge(intervals);
        assertArrayEquals(expected, result);
    }

    @Test
    void whenIntervalsAreMixedThenMergeOverlappingAndKeepNonOverlapping() {
        IntervalMerger intervalMerger = new IntervalMerger();
        int[][] intervals = new int[][] {{1, 4}, {0, 2}, {3, 5}};
        int[][] expected = new int[][] {{0, 5}};
        int[][] result = intervalMerger.merge(intervals);
        assertArrayEquals(expected, result);
    }
}
