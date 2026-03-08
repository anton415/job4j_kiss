package ru.job4j.ood.ocp;

public class OCPViolationExamples {

    /*
     * Пример 1. Нарушение OCP на уровне создаваемого объекта.
     */
    static class ReportService {
        public void send(Employee employee) {
            PdfReportSender sender = new PdfReportSender();
            // Нарушение OCP:
            // сервис жестко создает конкретный PdfReportSender.
            // Если потребуется отправка в HTML или Excel, придется менять этот класс,
            // а не расширять поведение через новую реализацию абстракции.
            sender.send(employee);
        }
    }

    static class PdfReportSender {
        public void send(Employee employee) {
            System.out.println("Send PDF report for " + employee.name());
        }
    }

    /*
     * Пример 2. Нарушение OCP на уровне поля класса.
     */
    static class PaymentService {
        private final SberbankGateway gateway = new SberbankGateway();
        // Нарушение OCP:
        // поле имеет конкретный тип SberbankGateway.
        // Если бизнес попросит TinkoffGateway или AlfaGateway,
        // придется изменять PaymentService и заменять тип поля.

        public void pay(int amount) {
            gateway.transfer(amount);
        }
    }

    static class SberbankGateway {
        public void transfer(int amount) {
            System.out.println("Transfer " + amount + " via Sberbank");
        }
    }

    /*
     * Пример 3. Нарушение OCP на уровне параметра метода.
     */
    static class Painter {
        public void draw(Rectangle rectangle) {
            // Нарушение OCP:
            // метод умеет работать только с Rectangle.
            // Когда появится Circle или Triangle, придется менять Painter:
            // добавлять новые перегрузки или условия вместо расширения через Shape.
            System.out.printf("Draw rectangle %dx%d%n", rectangle.width(), rectangle.height());
        }
    }

    record Employee(String name) {
    }

    record Rectangle(int width, int height) {
    }
}
