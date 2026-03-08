package ru.job4j.ood.srp.report;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * DTO для сериализации сотрудника в JSON и XML с уже отформатированными датами.
 */
@JsonbPropertyOrder({"name", "hired", "fired", "salary"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "hired", "fired", "salary"})
public class SerializedEmployee {
    private String name;
    private String hired;
    private String fired;
    private double salary;

    public SerializedEmployee() {
    }

    public SerializedEmployee(String name, String hired, String fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getHired() {
        return hired;
    }

    public String getFired() {
        return fired;
    }

    public double getSalary() {
        return salary;
    }
}
