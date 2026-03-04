package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    @DisplayName("Клиент может купить билет")
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertNotNull(ticket);
        assertInstanceOf(Ticket3D.class, ticket);
    }

    @Test
    @DisplayName("Добавленный сеанс доступен в каталоге")
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertEquals(session, sessions.get(0));
    }

    @Test
    @DisplayName("При попытке купить билет на несуществующее место выбрасывается исключение")
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, -1, 1, date));
    }

    @Test
    @DisplayName("Клиент не может приобрести билет на несуществующую дату.")
    public void whenBuyOnInvalidDateThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2025, Calendar.JANUARY, 1);
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, 1, 1, date));
    }

    @Test
    @DisplayName("Клиент не может купить один и тот же билет дважды для одного и того же сеанса.")
    public void whenBuySameSeatTwiceThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(account, 1, 1, date);
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, 1, 1, date));
    }

    @Test
    @DisplayName("Разные места в одном и том же сеансе могут быть проданы независимо")
    public void whenBuyDifferentSeatsThenBothAreSold() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket1 = cinema.buy(account, 1, 1, date);
        Ticket ticket2 = cinema.buy(account, 1, 2, date);
        assertNotNull(ticket1);
        assertNotNull(ticket2); 
    }

    @Test
    @DisplayName("Один и тот же ряд может быть действителен для разных разных дат")
    public void whenBuySameSeatDifferentSessionsThenBothAreSold() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.DAY_OF_MONTH, 1);
        Ticket ticket1 = cinema.buy(account, 1, 1, date1);
        Ticket ticket2 = cinema.buy(account, 1, 1, date2);
        assertNotNull(ticket1);
        assertNotNull(ticket2);
    }

    @Test
    @DisplayName("Кинотеатр должен показывать только те сеансы, которые соответствуют критериям поиска клиента")
    public void whenFindSessionsWithMatchingCriteriaThenReturnSessions() {
        Cinema cinema = new Cinema3D();
        Session session1 = new Session3D();
        Session session2 = new Session3D();
        cinema.add(session1);
        cinema.add(session2);
        List<Session> sessions = cinema.find(s -> s.equals(session1));
        assertEquals(1, sessions.size());
        assertEquals(session1, sessions.get(0));
    }

    @Test
    @DisplayName("Если ни один сеанс не соответствует фильтру, результат поиска должен быть пустым")
    public void whenFindSessionsWithNonMatchingCriteriaThenReturnEmptyList() {
        Cinema cinema = new Cinema3D();
        Session session1 = new Session3D();
        Session session2 = new Session3D();
        cinema.add(session1);
        cinema.add(session2);
        List<Session> sessions = cinema.find(s -> false);
        assertEquals(0, sessions.size());
    }
}