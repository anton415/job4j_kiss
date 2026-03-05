package ru.job4j.template;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class GeneratorTest {
    @Test
    @DisplayName("Возвращение правильной строки")
    public void whenProduceThenReturnString() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        String result = generator.produce(template, Map.of("name", "Petr", "subject", "you"));
        assertEquals("I am a Petr, Who are you?", result);
    }

    @Test
    @DisplayName("Программа должна учитывать, что в шаблоне есть ключи, которых нет в карте (Map) и кидать исключение")
    public void whenNotExistedKeysThenGetException() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> generator.produce(template, Map.of("nonExistingKey", "NonExistingValue")));
        assertEquals("The key is not found in the map: subject", exception.getMessage());
    }

    @Test
    @DisplayName("Программа должна учитывать, что в карте есть лишние ключи и тоже кидать исключение")
    public void whenExtraKeysThenGetException() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> generator.produce(template, Map.of("name", "Petr", "subject", "you", "extraKey", "ExtraValue")));
        assertEquals("The key is not found in the map: extraKey", exception.getMessage());
    }
}
