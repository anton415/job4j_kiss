package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Поиск интервала с максимальным количеством перекрытий
// время O(n log n), память O(n)
public class Main {
    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        // Пустой или отсутствующий входной список не содержит перекрытий.
        if (intervals == null || intervals.isEmpty()) {
            return new int[] {-1, -1};
        }

        // Сортируем интервалы по старту, чтобы идти слева направо.
        List<Interval> sorted = new ArrayList<>(intervals);
        sorted.sort(Comparator.comparingInt(i -> i.start));

        // Храним активные интервалы по минимальному окончанию.
        PriorityQueue<Interval> activeIntervals = new PriorityQueue<>(Comparator.comparingInt(i -> i.end));
        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;

        for (Interval interval : sorted) {
            // Пропускаем некорректные или нулевые интервалы.
            if (interval.start >= interval.end) {
                continue;
            }
            // Удаляем интервалы, которые уже закончились к текущему старту.
            while (!activeIntervals.isEmpty() && activeIntervals.peek().end <= interval.start) {
                activeIntervals.poll();
            }

            // Добавляем текущий интервал в активное окно.
            activeIntervals.offer(interval);

            // Фиксируем первый интервал с новым максимумом перекрытия.
            if (activeIntervals.size() > maxOverlap) {
                maxOverlap = activeIntervals.size();
                maxStart = interval.start;
                maxEnd = activeIntervals.peek().end;
            }
        }

        // Если валидных интервалов не было, возвращаем индикатор отсутствия ответа.
        if (maxOverlap == 0) {
            return new int[] {-1, -1};
        }
        return new int[] {maxStart, maxEnd};
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}
