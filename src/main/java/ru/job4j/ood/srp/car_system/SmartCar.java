package ru.job4j.ood.srp.car_system;

    // Причина нарушения SRP:
    // в одном классе смешаны управление движением, безопасность,
    // мультимедиа и диагностика. Это разные зоны ответственности.
/**
 * Класс нарушает SRP, так как выполняет несколько разных задач:
 * 1. Управление движением (drive)
 * 2. Безопасность (enableAlarm)
 * 3. Мультимедиа (playMusic)
 * 4. Диагностика (runDiagnostics)
 * Каждая из этих задач может изменяться по разным причинам, что делает класс менее устойчивым к изменениям и сложным для тестирования.
 */
class SmartCar implements CarSystem {
    @Override
    public void startTrip(String destination) {
        drive(destination);
        enableAlarm();
        playMusic();
        runDiagnostics();
    }

    private void drive(String destination) { }
    private void enableAlarm() { }
    private void playMusic() { }
    private void runDiagnostics() { }
}
