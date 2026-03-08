package ru.job4j.ood.srp.report;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Генерирует JSON-отчет на базе JSON-B.
 */
public class JsonReportEngine implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> parser;
    private final Jsonb jsonb;

    public JsonReportEngine(Store store) {
        this(store, new ReportDateTimeParser());
    }

    public JsonReportEngine(Store store, DateTimeParser<Calendar> parser) {
        this(store, parser, JsonbBuilder.create(new JsonbConfig().withFormatting(true)));
    }

    public JsonReportEngine(Store store, DateTimeParser<Calendar> parser, Jsonb jsonb) {
        this.store = store;
        this.parser = parser;
        this.jsonb = jsonb;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        /* Преобразуем доменную модель в DTO, чтобы сериализовать уже готовые строковые даты. */
        List<SerializedEmployee> employees = store.findBy(filter).stream()
                .map(this::serializeEmployee)
                .toList();
        return jsonb.toJson(employees);
    }

    private SerializedEmployee serializeEmployee(Employee employee) {
        return new SerializedEmployee(
                employee.getName(),
                parser.parse(employee.getHired()),
                parser.parse(employee.getFired()),
                employee.getSalary()
        );
    }
}
