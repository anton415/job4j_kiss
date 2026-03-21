package ru.job4j.algo.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        if (visitTimes == null || visitTimes.isEmpty()) {
            return new int[]{0, 0};
        }

        // Превращаем интервалы в события прихода/ухода.
        List<Event> events = new ArrayList<>();
        for (int[] visitTime : visitTimes) {
            if (visitTime == null || visitTime.length < 2) {
                continue;
            }
            events.add(new Event(visitTime[0], EventType.ARRIVAL));
            events.add(new Event(visitTime[1], EventType.DEPARTURE));
        }
        if (events.isEmpty()) {
            return new int[]{0, 0};
        }

        // Сортируем события по времени, при равенстве сначала приход.
        Collections.sort(events);

        int currentLoad = 0;
        int maxLoad = 0;
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;
        boolean maxIntervalOpened = false;

        // Идем слева направо и фиксируем первый интервал с глобальным максимумом.
        for (Event event : events) {
            if (event.type == EventType.ARRIVAL) {
                currentLoad++;
                if (currentLoad > maxLoad) {
                    maxLoad = currentLoad;
                    maxLoadStartTime = event.time;
                    maxLoadEndTime = event.time;
                    maxIntervalOpened = true;
                }
                continue;
            }
            if (maxIntervalOpened && currentLoad == maxLoad) {
                maxLoadEndTime = event.time;
                maxIntervalOpened = false;
            }
            currentLoad--;
        }

        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        final int time;
        final EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time != other.time) {
                return Integer.compare(this.time, other.time);
            }
            if (this.type == other.type) {
                return 0;
            }
            return this.type == EventType.ARRIVAL ? -1 : 1;
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}
