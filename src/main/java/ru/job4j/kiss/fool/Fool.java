package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    /**
     * Метод для определения значения Fizz, Buzz или FizzBuzz.
     * @param number - число для определения
     * @return строка "Fizz", "Buzz", "FizzBuzz" или число в виде строки
     */
    static String value(int number) {
        if (number % 15 == 0) {
            return "FizzBuzz";
        }
        if (number % 3 == 0) {
            return "Fizz";
        }
        if (number % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(number);
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(value(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!value(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}
