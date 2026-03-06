package ru.job4j.ood.srp.report;

import java.util.Comparator;
import java.util.function.Predicate;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

/**
 * Отдел HR попросил выводить сотрудников в порядке убывания зарплаты 
 * и убрать поля даты найма и увольнения.
 */
public class ReportHR implements Report {
    private final Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        store.findBy(filter).stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(employee -> text
                        .append(employee.getName()).append(" ")
                        .append(employee.getSalary()).append(System.lineSeparator()));
        return text.toString();
    }
}
