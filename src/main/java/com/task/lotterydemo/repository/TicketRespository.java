package com.task.lotterydemo.repository;

import com.task.lotterydemo.domain.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRespository extends LocalRepository<Ticket> {

    protected void updateIfExists(Ticket actual, Ticket updated) {
        actual.setLines(updated.getLines());
        actual.setModified(actual.getModified() + 1);
    }
    protected void updateStatus(Ticket ticket) {
            ticket.setChecked(true);
    }
}

