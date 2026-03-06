package ru.job4j.ood.srp.report;

import java.util.Calendar;
import java.util.Locale;
import java.util.function.Predicate;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

/**
 * В бухгалтерском отчете необходимо сделать конвертацию зарплаты в одну из валют.
 */
public class ReportAccounting implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> parser;
    private final CurrencyConverter converter;
    private final Currency sourceCurrency;
    private final Currency targetCurrency;

    public ReportAccounting(Store store, DateTimeParser<Calendar> parser) {
        this(store, parser, new InMemoryCurrencyConverter(), Currency.RUB, Currency.USD);
    }

    public ReportAccounting(Store store, DateTimeParser<Calendar> parser,
                            CurrencyConverter converter, Currency sourceCurrency, Currency targetCurrency) {
        this.store = store;
        this.parser = parser;
        this.converter = converter;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : store.findBy(filter)) {
            double convertedSalary = converter.convert(sourceCurrency, worker.getSalary(), targetCurrency);
            text.append(worker.getName()).append(" ")
                    .append(parser.parse(worker.getHired())).append(" ")
                    .append(parser.parse(worker.getFired())).append(" ")
                    .append(String.format(Locale.US, "%.2f", convertedSalary))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
