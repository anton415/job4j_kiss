package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    @DisplayName("Должен найти отрезок [3, 4] при частично пересекающихся интервалах")
    public void whenIntervalsOverlapThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = Main.findMaxOverlapInterval(intervals);
        assertArrayEquals(new int[] {3, 4}, result);
    }

    @Test
    @DisplayName("Должен найти отрезок [2, 3] при последовательных пересечениях")
    public void whenSequentialIntervalsThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(4, 6));
        int[] result = Main.findMaxOverlapInterval(intervals);
        assertArrayEquals(new int[] {2, 3}, result);
    }

    @Test
    @DisplayName("Должен найти отрезок [2, 3] при одном внешнем и нескольких непересекающихся интервалах")
    public void whenNonOverlappingIntervalsThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 10));
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(4, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 9));
        int[] result = Main.findMaxOverlapInterval(intervals);
        assertArrayEquals(new int[] {2, 3}, result);
    }

    @Test
    @DisplayName("Должен вернуть единственный интервал [1, 10]")
    public void whenSingleIntervalThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 10));
        int[] result = Main.findMaxOverlapInterval(intervals);
        assertArrayEquals(new int[] {1, 10}, result);
    }

    @Test
    @DisplayName("Должен вернуть [-1, -1], если входной список пуст")
    public void whenNoIntervalsThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        int[] result = Main.findMaxOverlapInterval(intervals);
        assertArrayEquals(new int[] {-1, -1}, result);
    }
}
