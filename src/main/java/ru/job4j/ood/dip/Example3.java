package ru.job4j.ood.dip;

public class Example3 {
    public String loadUser(String id) {
        // Нарушение DIP: Example3 создает и использует
        // конкретную реализацию репозитория DbUserRepository.
        DbUserRepository repo = new DbUserRepository();
        return repo.findById(id);
    }
}

class DbUserRepository {
    public String findById(String id) {
        return "user-" + id;
    }
}
