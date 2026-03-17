package ru.job4j.algo.hash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LongestUniqueSubstringTest {
    @Test
    public void whenStringIsEmpty() {
        String str = "";
        assertEquals("", LongestUniqueSubstring.longestUniqueSubstring(str));
    }

    @Test
    public void whenStringHasUniqueCharacters() {
        String str = "abcde";
        assertEquals("abcde", LongestUniqueSubstring.longestUniqueSubstring(str));
    }

    @Test
    public void whenStringHasRepeatedCharacters() {
        String str = "abcbcde";
        assertEquals("bcde", LongestUniqueSubstring.longestUniqueSubstring(str));
    }

    @Test
    public void whenStringHasAllRepeatedCharacters() {
        String str = "aaaaa";
        assertEquals("a", LongestUniqueSubstring.longestUniqueSubstring(str));
    }
}