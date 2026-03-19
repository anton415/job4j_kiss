package ru.job4j.algo.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Brackets {
    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            // switch rule
            switch (ch) {
                case '(', '[', '{' -> stack.push(ch);
                case ')', ']', '}' -> {
                    if (stack.isEmpty()) {
                        return false;
                    }   
                    char open = stack.pop();
                    if ((ch == ')' && open != '(')
                            || (ch == ']' && open != '[')
                            || (ch == '}' && open != '{')) {
                        return false;
                    }
                }
                default -> {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
