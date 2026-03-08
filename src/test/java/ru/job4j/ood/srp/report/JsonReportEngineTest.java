package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверяет точный формат JSON-отчета.
 */
public class JsonReportEngineTest {
    @Test
    public void whenGenerated() {
        Store store = new MemoryStore();
        Employee employee = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);
        Employee employee1 = new Employee("Jane Smith",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                6000.0);
        store.add(employee);
        store.add(employee1);
        Report engine = new JsonReportEngine(store);
        /* Сравниваем строку целиком, потому что важны и порядок полей, и форматирование JSON. */
        String expected = """
                [
                    {
                        "name": "John Doe",
                        "hired": "08:06:2023 17:41",
                        "fired": "08:06:2023 17:41",
                        "salary": 5000.0
                    },
                    {
                        "name": "Jane Smith",
                        "hired": "08:06:2023 17:41",
                        "fired": "08:06:2023 17:41",
                        "salary": 6000.0
                    }
                ]""";
        assertEquals(expected, engine.generate(em -> true));
    }
}
