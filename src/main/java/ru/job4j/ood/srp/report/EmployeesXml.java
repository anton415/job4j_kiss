package ru.job4j.ood.srp.report;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * JAXB-обертка для списка сотрудников с корневым тегом employees.
 */
@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesXml {
    /**
     * Каждый элемент списка сериализуется как отдельный тег employee.
     */
    @XmlElement(name = "employee")
    private List<SerializedEmployee> employees = new ArrayList<>();

    public EmployeesXml() {
    }

    public EmployeesXml(List<SerializedEmployee> employees) {
        this.employees = employees;
    }

    public List<SerializedEmployee> getEmployees() {
        return employees;
    }
}
