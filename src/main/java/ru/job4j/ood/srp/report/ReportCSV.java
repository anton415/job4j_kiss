package ru.job4j.ood.srp.report;

import java.util.Calendar;
import java.util.function.Predicate;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

/**
 * Для отдела программистов потребовался отчет в формате CSV.
 */
public class ReportCSV implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> parser;

    public ReportCSV(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(",")
                    .append(parser.parse(employee.getHired())).append(",")
                    .append(parser.parse(employee.getFired())).append(",")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
