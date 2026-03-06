package ru.job4j.ood.srp.data_output;

/**
 * Класс нарушает SRP, так как выполняет несколько разных задач:
 * 1. Валидация данных (isValid)
 * 2. Форматирование данных (format)
 * 3. Сохранение данных в базу (saveToDb)
 * 4. Вывод данных на консоль (printToConsole)
 * Каждая из этих задач может изменяться по разным причинам, что делает класс менее устойчивым к изменениям и сложным для тестирования.
 */
class DataOutputImpl implements DataOutput {
    @Override
    public void process(String rawData) {
        if (!isValid(rawData)) {
            throw new IllegalArgumentException("Некорректные данные");
        }
        String formatted = format(rawData);
        saveToDb(formatted);
        printToConsole(formatted);
    }

    private boolean isValid(String rawData) {
        return rawData != null && !rawData.isBlank();
    }

    private String format(String rawData) {
        return rawData.trim().toUpperCase();
    }

    private void saveToDb(String data) {
        // SQL/JDBC логика
    }

    private void printToConsole(String data) {
        System.out.println(data);
    }
}