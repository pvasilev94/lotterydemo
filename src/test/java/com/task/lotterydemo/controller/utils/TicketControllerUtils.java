package com.task.lotterydemo.controller.utils;

import com.task.lotterydemo.domain.Ticket;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class TicketControllerUtils {

    public static ResultMatcher ticketIsCorrect(Ticket expected) {
        return ticketIsCorrect(expected.getId(), expected);
    }

    public static ResultMatcher ticketIsCorrect(Long expectedId, Ticket expected) {
        return new TicketResultMatcher().addMatcher(jsonPath("$.id").value(expectedId));
    }

    public static ResultMatcher updateTicketCorrect(Long originalId, Ticket expected) {
        return ticketIsCorrect(originalId, expected);
    }
}
