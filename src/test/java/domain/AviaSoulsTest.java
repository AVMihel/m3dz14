package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AviaSoulsTest {

    @Test
    public void shouldComparedPrice() {
        Ticket ticket1 = new Ticket("Москва", "Хабаровск", 10000, 10, 12);
        Ticket ticket2 = new Ticket("Москва", "Хабаровск", 10100, 10, 12);
        Ticket ticket3 = new Ticket("Москва", "Хабаровск", 10000, 10, 12);

        int expected1 = -1;
        int actual1 = ticket1.compareTo(ticket2);
        Assertions.assertEquals(expected1, actual1);

        int expected2 = 1;
        int actual2 = ticket2.compareTo(ticket1);
        Assertions.assertEquals(expected2, actual2);

        int expected3 = 0;
        int actual3 = ticket1.compareTo(ticket3);
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    public void shouldSearchAndSortTickets() {
        AviaSouls aviaSouls = new AviaSouls();

        Ticket ticket1 = new Ticket("Новосибирск", "Пекин", 10000, 9, 11);
        Ticket ticket2 = new Ticket("Новосибирск", "Пекин", 11050, 11, 13);
        Ticket ticket3 = new Ticket("Новосибирск", "Пекин", 11200, 10, 12);
        Ticket ticket4 = new Ticket("Новосибирск", "Пекин", 13000, 7, 11);
        Ticket ticket5 = new Ticket("Новосибирск", "Пекин", 12300, 8, 17);
        Ticket ticket6 = new Ticket("Новосибирск", "Екатеринбург", 10300, 4, 19);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Ticket[] actual = aviaSouls.search("Новосибирск", "Пекин");

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket5, ticket4};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNoResults() {
        AviaSouls aviaSouls = new AviaSouls();

        Ticket ticket1 = new Ticket("Ереван", "Караганда", 1000, 9, 11);
        Ticket ticket2 = new Ticket("Ереван", "Караганда", 1050, 11, 13);
        Ticket ticket3 = new Ticket("Ереван", "Караганда", 1200, 10, 12);
        Ticket ticket4 = new Ticket("Ереван", "Караганда", 1000, 8, 10);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);

        Ticket[] actual = aviaSouls.search("Белгород", "Симферополь");

        Ticket[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSingleResult() {
        AviaSouls aviaSouls = new AviaSouls();

        Ticket ticket1 = new Ticket("Москва", "Уссурийск", 10000, 9, 11);
        Ticket ticket2 = new Ticket("Москва", "Уссурийск", 11050, 11, 13);
        Ticket ticket3 = new Ticket("Москва", "Уссурийск", 11200, 10, 12);
        Ticket ticket4 = new Ticket("Москва", "Бобруйск", 13000, 8, 15);
        Ticket ticket5 = new Ticket("Москва", "Уссурийск", 12300, 6, 18);
        Ticket ticket6 = new Ticket("Москва", "Уссурийск", 10300, 3, 11);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Ticket[] actual = aviaSouls.search("Москва", "Бобруйск");

        Ticket[] expected = {ticket4};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsFlghtTime() {
        Ticket ticket1 = new Ticket("Киров", "Суздаль", 6000, 3, 17);
        Ticket ticket2 = new Ticket("Киров", "Суздаль", 7100, 10, 20);
        Ticket ticket3 = new Ticket("Киров", "Суздаль", 5000, 5, 22);
        Ticket ticket4 = new Ticket("Киров", "Суздаль", 8000, 15, 24);
        Ticket ticket5 = new Ticket("Киров", "Суздаль", 7000, 8, 17);

        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected1 = -1;
        int actual1 = comparator.compare(ticket2, ticket1);
        Assertions.assertEquals(expected1, actual1);

        int expected2 = 1;
        int actual2 = comparator.compare(ticket3, ticket2);
        Assertions.assertEquals(expected2, actual2);

        int expected3 = -1;
        int actual3 = comparator.compare(ticket4, ticket3);
        Assertions.assertEquals(expected3, actual3);

        int expected4 = 0;
        int actual4 = comparator.compare(ticket5, ticket4);
        Assertions.assertEquals(expected4, actual4);
    }

    @Test
    public void shouldSearchAndSortBy() {
        AviaSouls aviaSouls = new AviaSouls();

        Ticket ticket1 = new Ticket("Москва", "Омск", 6000, 3, 17);
        Ticket ticket2 = new Ticket("Москва", "Бруклин", 7100, 10, 20);
        Ticket ticket3 = new Ticket("Москва", "Омск", 5000, 5, 22);
        Ticket ticket4 = new Ticket("Чебоксары", "Омск", 8000, 15, 24);
        Ticket ticket5 = new Ticket("Москва", "Омск", 7000, 8, 17);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] actual = aviaSouls.searchAndSortBy("Москва", "Омск", comparator);
        Ticket[] expected = {ticket5, ticket1, ticket3};

        Assertions.assertArrayEquals(expected, actual);
    }
}
