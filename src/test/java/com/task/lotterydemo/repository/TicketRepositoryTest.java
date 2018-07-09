package com.task.lotterydemo.repository;

import static com.task.lotterydemo.utils.TicketTestUtils.*;
import com.task.lotterydemo.domain.Ticket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketRepositoryTest {

    public static final long NONVIABLE_ID = 999;

    @Autowired
    private TicketRespository repository;

    @Before
    public void setUp() {
        repository.clear();
    }

    @Test
    public void testFindNonExistantTicketOptionalIsNotPresent() throws Exception {
        assertNoExistingTickets();
        Optional<Ticket> ticket = repository.findById(NONVIABLE_ID);
        Assert.assertFalse(ticket.isPresent());
    }

    @Test
    public void testFindNonExistantTicketOptionalIsPresent() throws Exception {
        Ticket ticket = createDummyTicketResult10();
        Optional<Ticket> ticketTest = repository.findById(ticket.getId());
        Assert.assertTrue(ticketTest.isPresent());
    }

    @Test
    public void testFindExistingTicketAllValuesMatch() throws Exception {
        Ticket ticket = createDummyTicketResult10();
        Optional<Ticket> ticketTest = repository.findById(ticket.getId());
        assertTicketValuesMatch(ticket, ticketTest);
    }

    @Test
    public void allIsCorrectWithTicketCountOne() throws Exception {
        ticketCountisCorrect(1);
    }

    @Test
    public void allIsCorrectWithTicketCountTwo() throws Exception {
        ticketCountisCorrect(2);
    }
    private void currentTicketCount(int number) {
        Assert.assertEquals(number, repository.getSize());
    }

    private void ticketCountisCorrect(int number) {
        createNumberOfTickets(number);
        List<Ticket> ticketList = repository.findAll();
        Assert.assertEquals(number, ticketList.size());
    }

    private void createNumberOfTickets(int number) {
        List<Ticket> dummyList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            dummyList.add(createDummyTicketResult10());
        }
    }


    private Ticket createDummyTicketResult10() {
        Ticket dummy = repository.create(generateTestTicket(generateTestLineResult10()));
        return dummy;
    }
    private void assertNoExistingTickets() {
        assertExistingTicketCount(0);
    }

    private void assertExistingTicketCount(int count) {
        Assert.assertEquals(count, repository.getSize());
    }

}
