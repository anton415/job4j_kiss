package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Генерирует XML-отчет на базе JAXB.
 */
public class XmlReportEngine implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> parser;
    private final Marshaller marshaller;

    public XmlReportEngine(Store store) {
        this(store, new ReportDateTimeParser());
    }

    public XmlReportEngine(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
        this.marshaller = createMarshaller();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        /* JAXB получает подготовленные DTO, чтобы формат дат совпадал с остальными отчетами. */
        List<SerializedEmployee> employees = store.findBy(filter).stream()
                .map(this::serializeEmployee)
                .toList();
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new EmployeesXml(employees), writer);
            return writer.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Cannot generate XML report", e);
        }
    }

    private Marshaller createMarshaller() {
        try {
            JAXBContext context = JAXBContext.newInstance(EmployeesXml.class);
            Marshaller marshaller = context.createMarshaller();
            /* Включаем читаемый отформатированный XML и задаем отступ как в ожидаемом выводе. */
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("org.glassfish.jaxb.indentString", "    ");
            return marshaller;
        } catch (JAXBException e) {
            throw new IllegalStateException("Cannot configure XML marshaller", e);
        }
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
