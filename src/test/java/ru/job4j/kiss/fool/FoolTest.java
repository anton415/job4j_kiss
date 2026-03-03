package ru.job4j.kiss.fool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FoolTest {

    @Test
    void whenValueCalculatedThenExpectedResultReturned() {
        assertEquals("1", Fool.value(1));
        assertEquals("Fizz", Fool.value(3));
        assertEquals("Buzz", Fool.value(5));
        assertEquals("FizzBuzz", Fool.value(15));
        assertEquals("98", Fool.value(98));
    }
}
