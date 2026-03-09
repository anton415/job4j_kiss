package ru.job4j.ood.lsp;

public class LSPViolationExamples {

    /*
     * Пример 1. Подкласс усиливает предусловие метода.
     */
    static class BonusAccount {
        public void addBonus(int points) {
            if (points <= 0) {
                throw new IllegalArgumentException("Points must be positive");
            }
            System.out.println("Bonus points added: " + points);
        }
    }

    static class PremiumBonusAccount extends BonusAccount {
        @Override
        public void addBonus(int points) {
            if (points < 100) {
                // Нарушение LSP:
                // базовый класс принимает любое положительное количество баллов,
                // а подкласс усиливает предусловие и требует минимум 100.
                // Код, который корректно работал с BonusAccount, сломается
                // при подстановке PremiumBonusAccount.
                throw new IllegalArgumentException("Minimum 100 bonus points required");
            }
            System.out.println("Premium bonus points added: " + points);
        }
    }

    /*
     * Пример 2. Подкласс ослабляет постусловие метода.
     */
    static class Order {
        private final String number;

        public Order(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }
    }

    static class OrderRepository {
        public Order findByNumber(String number) {
            if (number == null || number.isBlank()) {
                throw new IllegalArgumentException("Order number is required");
            }
            // Клиент ожидает, что метод вернет корректный Order,
            // если входные данные валидны.
            return new Order(number);
        }
    }

    static class CacheOrderRepository extends OrderRepository {
        @Override
        public Order findByNumber(String number) {
            // Нарушение LSP:
            // базовый контракт обещал вернуть объект Order для валидного номера,
            // а подкласс возвращает null.
            // Клиент, написанный под OrderRepository, не обязан делать
            // дополнительную null-проверку и может получить ошибку.
            return null;
        }
    }

    /*
     * Пример 3. Подкласс ломает инвариант базового класса.
     */
    static class Rectangle {
        protected int width;
        protected int height;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int area() {
            return width * height;
        }
    }

    static class Square extends Rectangle {
        @Override
        public void setWidth(int width) {
            // Нарушение LSP:
            // клиент Rectangle ожидает, что ширина и высота меняются независимо.
            // В Square изменение ширины автоматически меняет и высоту,
            // то есть поведение подтипа отличается от контракта базового типа.
            this.width = width;
            this.height = width;
        }

        @Override
        public void setHeight(int height) {
            this.width = height;
            this.height = height;
        }
    }

    static class GeometryService {
        public boolean hasExpectedArea(Rectangle rectangle) {
            rectangle.setWidth(4);
            rectangle.setHeight(5);
            // Для обычного Rectangle ожидается площадь 20.
            // При подстановке Square получится 25, потому что стороны уже
            // нельзя менять независимо.
            return rectangle.area() == 20;
        }
    }
}
