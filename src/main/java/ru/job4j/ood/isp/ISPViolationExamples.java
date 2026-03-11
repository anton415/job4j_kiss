package ru.job4j.ood.isp;

public class ISPViolationExamples {

    /*
     * Пример 1. Интерфейс заставляет принтер зависеть от методов сканирования и факса.
     */
    interface OfficeDevice {
        void print(String document);

        void scan(String document);

        void fax(String phone, String document);
    }

    static class Printer implements OfficeDevice {
        @Override
        public void print(String document) {
            System.out.println("Print: " + document);
        }

        @Override
        public void scan(String document) {
            // Нарушение ISP:
            // обычный принтер не умеет сканировать, но вынужден зависеть от этого метода
            // из-за слишком широкого интерфейса OfficeDevice.
            throw new UnsupportedOperationException("Printer cannot scan");
        }

        @Override
        public void fax(String phone, String document) {
            throw new UnsupportedOperationException("Printer cannot fax");
        }
    }

    /*
     * Пример 2. Интерфейс заставляет онлайн-киоск поддерживать оплату наличными.
     */
    interface PaymentTerminal {
        void payByCard(int amount);

        void payByCash(int amount);

        void returnPayment(int amount);
    }

    static class OnlineCheckout implements PaymentTerminal {
        @Override
        public void payByCard(int amount) {
            System.out.println("Paid by card: " + amount);
        }

        @Override
        public void payByCash(int amount) {
            // Нарушение ISP:
            // онлайн-оформление заказа работает только с безналичной оплатой,
            // но интерфейс вынуждает реализовать и неиспользуемый сценарий с наличными.
            throw new UnsupportedOperationException("Online checkout cannot accept cash");
        }

        @Override
        public void returnPayment(int amount) {
            System.out.println("Return: " + amount);
        }
    }

    /*
     * Пример 3. Интерфейс делает пингвина зависимым от умения летать.
     */
    interface Bird {
        void walk();

        void swim();

        void fly();
    }

    static class Penguin implements Bird {
        @Override
        public void walk() {
            System.out.println("Penguin walks");
        }

        @Override
        public void swim() {
            System.out.println("Penguin swims");
        }

        @Override
        public void fly() {
            // Нарушение ISP:
            // для пингвина метод fly() не имеет смысла,
            // значит абстракция Bird выделена слишком грубо.
            throw new UnsupportedOperationException("Penguin cannot fly");
        }
    }
}
