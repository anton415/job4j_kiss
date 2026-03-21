package ru.job4j.algo.recursion;

public class Recursion {
    public int sum(int summary, int index) {
        if (index > 0) {
            summary += index;
            index--;
            summary = sum(summary, index);
        }
        return summary;
    }

    public int loop(int summary, int index) {
        for (int i = index; i > 0; i--) {
            summary += i;
        }
        return summary;
    }

    public long factorialLoop(int f) {
        long result = 1L;
        if (f > 0) {
            for (int i = 1; i <= f; i++) {
                result = result * i;
            }
        }
        return result;
    }

    public long factorialRecursion(long index) {
        // Базовый случай: 0! и 1! равны 1.
        if (index <= 1) {
            return 1L;
        }
        // Рекурсивный шаг: n! = n * (n - 1)!.
        return index * factorialRecursion(index - 1);
    }

    public long fibonacciLoop(int n) {
        long result = 0L;
        if (n == 1) {
            result = 1L;
        } else if (n > 1) {
            long f1 = 1L;
            long f2 = 1L;
            for (int i = 0; i < (n - 2); i++) {
                result = f2 + f1;
                f1 = f2;
                f2 = result;
            }
        }
        return result;        
    }

    public long fibonacciRecursion(int n) {
        // Базовый случай: первое число Фибоначчи.
        if (n == 0) {
            return 0L;
        }
        // Базовый случай: второе число Фибоначчи.
        if (n == 1) {
            return 1L;
        }
        // Рекурсивный шаг: F(n) = F(n - 1) + F(n - 2).
        return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }
}
