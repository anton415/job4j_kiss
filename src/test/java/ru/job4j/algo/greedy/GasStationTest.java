package ru.job4j.algo.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GasStationTest {
    @Test
    @DisplayName("Должен вернуть старт 3 для базового примера")
    void whenThatTestCase1() {
        GasStation gasStation = new GasStation();
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        int result = gasStation.canCompleteCircuit(gas, cost);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("Должен вернуть -1, если полный круг невозможен")
    void whenThatTestCase2() {
        GasStation gasStation = new GasStation();
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        int result = gasStation.canCompleteCircuit(gas, cost);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Должен вернуть старт 4 при переносе старта по ходу обхода")
    void whenThatTestCase3() {
        GasStation gasStation = new GasStation();
        int[] gas = {5, 1, 2, 3, 4};
        int[] cost = {4, 4, 1, 5, 1};
        int result = gasStation.canCompleteCircuit(gas, cost);
        assertEquals(4, result);
    }

    @Test
    @DisplayName("Должен вернуть старт 0 при достаточном балансе с начала")
    void whenThatTestCase4() {
        GasStation gasStation = new GasStation();
        int[] gas = {3, 1, 1};
        int[] cost = {1, 2, 2};
        int result = gasStation.canCompleteCircuit(gas, cost);
        assertEquals(0, result);
    }
}
