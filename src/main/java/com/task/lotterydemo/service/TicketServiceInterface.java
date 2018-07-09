package com.task.lotterydemo.service;

import com.task.lotterydemo.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketServiceInterface {
        Ticket createTicket(Ticket ticket);
        List<Ticket> findAll();
        Optional<Ticket> findById(Long id);
        boolean update(Long id, Ticket ticket);
        boolean delete(Long id);
}
