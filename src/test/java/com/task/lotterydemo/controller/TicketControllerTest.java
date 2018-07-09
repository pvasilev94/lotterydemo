package com.task.lotterydemo.controller;

import static com.task.lotterydemo.utils.TicketTestUtils.*;
import static com.task.lotterydemo.controller.utils.TicketControllerUtils.*;

import com.task.lotterydemo.domain.Ticket;
import com.task.lotterydemo.repository.TicketRespository;
import com.task.lotterydemo.service.TicketService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketControllerTest extends ControllerIntegrationTest {

    private static final String TEST_PAYLOAD = "{\n" +
            "    \"lines\": [\n" +
            "        {\n" +
            "            \"number_1\": 1,\n" +
            "            \"number_2\": 2,\n" +
            "            \"number_3\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"number_1\": 1,\n" +
            "            \"number_2\": 2,\n" +
            "            \"number_3\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"number_1\": 1,\n" +
            "            \"number_2\": 2,\n" +
            "            \"number_3\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"number_1\": 1,\n" +
            "            \"number_2\": 3,\n" +
            "            \"number_3\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"number_1\": 1,\n" +
            "            \"number_2\": 3,\n" +
            "            \"number_3\": 2\n" +
            "        }\n" +
            "    ]\n" +
            "}";


    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRespository ticketRespository;

    @Before
    public void setUp() {
        ticketRespository.clear();
    }

    @Test
    public void correctResponseGetAllTickets() throws Exception {
        assertNoTickets();
        getTicket()
                .andExpect(status().isOk())
                .andExpect(content().string(("[]")));
    }

    @Test
    public void InsertTicketAndGetCorrectResponse() throws Exception {
        Ticket ticket = generateTestTicket(generateTestLineResult10());
        ticketService.createTicket(ticket);
        assertTicketSizeIs(1);
        getTicket()
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateNoTicketCorrectResponse() throws Exception {
        assertNoTickets();
        updateTicket(1, new Ticket())
                .andExpect(status().isNotFound());
    }
    @Test
    public void testUpdateTicket() throws Exception {
        assertNoTickets();
        Ticket ver1 = ticketService.createTicket(generateTestTicket(generateTestLineResult10()));
        Ticket ver2 = generateTestTicket(generateTestLineResult5());
        assertTicketSizeIs(1);
        updateTicket(ver1.getId(), ver2)
                .andExpect(status().isOk())
                .andExpect(updateTicketCorrect(ver1.getId(), ver2));
    }
    @Test
    public void testUpdateTicketWithCheckedLines() throws Exception {
        assertNoTickets();
        Ticket ticket = generateTestTicket(generateTestLineResult10());
        ticketService.createTicket(ticket);
        checkTicket();
        updateTicket(ticket.getId(), generateTestTicket(generateTestLineResult5()))
                .andExpect(status().isMethodNotAllowed());
    }
    @Test
    public void deleteNonExistantTicketCorrectResponse() throws Exception {
        assertNoTickets();
        deleteTicket(1)
                .andExpect(status().isNotFound());
    }
    @Test
    public void deleteExistantTicketCorrectResponse() throws Exception {
        Ticket ticket = ticketService.createTicket(generateTestTicket(generateTestLineResult5()));
        assertTicketSizeIs(1);
        deleteTicket(ticket.getId())
                .andExpect(status().isNoContent());
    }
    @Test
    public void createNewOTicketCorrectResponse() throws Exception {
        assertNoTickets();
        createTicket(TEST_PAYLOAD)
                .andExpect(status().isCreated())
                .andExpect(ticketIsCorrect(getTicketNew()));
    }
    private ResultActions createTicket(String payload) throws Exception {
        return post("/ticket", payload);
    }
    private ResultActions deleteTicket(long id) throws Exception {
        return delete("/ticket/{id}", id);
    }
    private ResultActions updateTicket(long id, Ticket updatedTicket) throws Exception {
        return put("/ticket/{id}", updatedTicket, String.valueOf(id));
    }
    private ResultActions getTicket() throws Exception {
        return get("/ticket");
    }
    private ResultActions checkTicket() throws Exception {
        return get("/ticket/check");
    }
    private void assertNoTickets() {
        assertTicketSizeIs(0);
    }
    private void assertTicketSizeIs(int number) {
        Assert.assertEquals(number, ticketService.findAll().size());
    }
    private Ticket getTicketNew() {
            List<Ticket> ticketList = ticketService.findAll();
            return ticketList.get(ticketList.size() - 1);
    }


}
