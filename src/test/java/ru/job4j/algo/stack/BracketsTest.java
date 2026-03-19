package ru.job4j.algo.stack;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BracketsTest {

    private final Brackets brackets = new Brackets();

    @Test
    void givenValidBracketsWhenIsValidThenTrue() {
        assertTrue(brackets.isValid("()"));
        assertTrue(brackets.isValid("()[]{}"));
        assertTrue(brackets.isValid("{[]}"));
    }

    @Test
    void givenInvalidBracketsWhenIsValidThenFalse() {
        assertFalse(brackets.isValid("(]"));
        assertFalse(brackets.isValid("([)]"));
        assertFalse(brackets.isValid("]"));
        assertFalse(brackets.isValid("{"));
        assertTrue(brackets.isValid(""));
    }
}