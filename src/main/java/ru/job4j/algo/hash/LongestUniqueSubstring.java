package ru.job4j.algo.hash;

import java.util.HashMap;

/**
 * Вычислительная сложность: O(n) где n - длина строки
 * Память: O(min(m, n)), где m - размер алфавита, n - длина строки
 */
public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        //символ -> последний индекс
        HashMap<Character, Integer> lastIndexMap = new HashMap<>();
        // для левой границы диапозона
        int left = 0;
        // для начала лучшего ответа
        int bestLength = 0;
        // для длины лучшего ответа
        int bestStart = 0;

        for(int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);
            if (lastIndexMap.containsKey(currentChar)) {
                // если символ уже был, то двигаем левую границу
                left = Math.max(left, lastIndexMap.get(currentChar) + 1);
            }
            // обновляем последний индекс текущего символа
            lastIndexMap.put(currentChar, right);
            // проверяем, нужно ли обновить лучший ответ
            if (right - left + 1 > bestLength) {
                bestLength = right - left + 1;
                bestStart = left;
            }
        }

        return str.substring(bestStart, bestStart + bestLength);
    }
}
