package ru.job4j.ood.srp.report;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

public class ReportCSVTest {
    @Test
    public void whenOldGenerated() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportCSV(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(",")
                .append(parser.parse(worker.getHired())).append(",")
                .append(parser.parse(worker.getFired())).append(",")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertEquals(expected.toString(), engine.generate(employee -> true));
    }
}
