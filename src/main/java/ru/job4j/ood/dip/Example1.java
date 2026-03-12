package ru.job4j.ood.dip;

public class Example1 {
    public void send() {
        // Нарушение DIP: высокий уровень (Example1) зависит от конкретного класса EmailSender,
        // а не от абстракции (например, интерфейса Sender).
        EmailSender sender = new EmailSender();
        sender.send("hello");
    }
}

class EmailSender {
    public void send(String text) {
        System.out.println(text);
    }
}
