package ru.job4j.algo.recursion;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecursionTest {
    @Test
    @DisplayName("Рекурсивная сумма должна совпадать с реализацией через цикл")
    void checkSummary() {
        Recursion recursion = new Recursion();
        boolean result = recursion.loop(1, 5) == recursion.sum(1, 5);
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка факториала")
    void checkFactorial() {
        Recursion recursion = new Recursion();
        boolean result = recursion.factorialLoop(0) == recursion.factorialRecursion(0);
        assertTrue(result);
        result = recursion.factorialLoop(1) == recursion.factorialRecursion(1);
        assertTrue(result);
        result = recursion.factorialLoop(5) == recursion.factorialRecursion(5);
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка Фибоначчи")
    void checkFibonacci() {
        Recursion recursion = new Recursion();
        boolean result = recursion.fibonacciLoop(0) == recursion.fibonacciRecursion(0);
        assertTrue(result);
        result = recursion.fibonacciLoop(1) == recursion.fibonacciRecursion(1);
        assertTrue(result);
        result = recursion.fibonacciLoop(5) == recursion.fibonacciRecursion(5);
        assertTrue(result);
    }
}
