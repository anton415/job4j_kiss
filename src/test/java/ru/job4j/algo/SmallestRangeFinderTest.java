package ru.job4j.algo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;


public class SmallestRangeFinderTest {
    @Test
    public void whenFindSmallestRangeUniqueElementsThenReturnsExpectedRange() {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] expectedRange = {0, 2};
        assertArrayEquals(expectedRange, SmallestRangeFinder.findSmallestRange(nums, k));
    }

    @Test
    public void whenFindSmallestRangeRepeatedElementsThenReturnsExpectedRange() {
        int[] nums = {1, 2, 3, 3, 5, 6, 7};
        int k = 4;
        int[] expectedRange = {3, 6};
        assertArrayEquals(expectedRange, SmallestRangeFinder.findSmallestRange(nums, k));
    }

    @Test
    public void whenNotFound() {
        int[] nums = {1, 2, 3, 3, 3};
        int k = 4;
        int[] expectedRange = null;
        assertArrayEquals(expectedRange, SmallestRangeFinder.findSmallestRange(nums, k));
    }
}
