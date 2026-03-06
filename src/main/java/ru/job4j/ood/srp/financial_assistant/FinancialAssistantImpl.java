package ru.job4j.ood.srp.financial_assistant;

/** 
 * Класс нарушает SRP, так как выполняет несколько разных задач:
 * 1. Отслеживание бюджета (trackBudget)
 * 2. Финансовые советы (giveInvestmentAdvice)
 * 3. Психологическая поддержка (providePsychologicalSupport)
 * 4. Развлечения (tellJoke, playMusic)
 * Каждая из этих задач может изменяться по разным причинам, что делает класс менее устойчивым к изменениям и сложным для тестирования.
 */
class FinancialAssistantImpl implements FinancialAssistant {
    @Override
    public void handleDailySession() {
        trackBudget();
        giveInvestmentAdvice();
        providePsychologicalSupport();
        tellJoke();
        playMusic();
    }

    private void trackBudget() { }
    private void giveInvestmentAdvice() { }
    private void providePsychologicalSupport() { }
    private void tellJoke() { }
    private void playMusic() { }
}