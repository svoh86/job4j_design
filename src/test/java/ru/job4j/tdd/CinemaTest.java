package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CinemaTest {
    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 6, 18, 10, 45);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    /**
     * Тест, когда место занято.
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyFail() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 6, 18, 10, 45);
        Ticket ticket = cinema.buy(account, 10, 10, date);
        Ticket ticket2 = cinema.buy(account, 10, 10, date);
    }

    /**
     * Тест, когда место не существует.
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceIsNotExist() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 6, 18, 10, 45);
        Ticket ticket = cinema.buy(account, 1000, 10, date);
    }

    /**
     * Тест, когда дата некорректная.
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenDateWrong() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2030, 6, 18, 10, 45);
    }
}