package ru.job4j.ood.srp.report;

import java.util.function.Predicate;

import ru.job4j.ood.srp.model.Employee;

public interface Report {
    String generate(Predicate<Employee> filter);
}