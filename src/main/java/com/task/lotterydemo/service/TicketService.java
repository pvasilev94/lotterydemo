package com.task.lotterydemo.service;

import com.task.lotterydemo.domain.Line;
import com.task.lotterydemo.domain.Ticket;
import com.task.lotterydemo.repository.TicketRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TicketService implements TicketServiceInterface {

    private static final int RESULT_10 = 10;
    private static final int RESULT_5 = 5;
    private static final int RESULT_1 = 1;
    private static final int RESULT_0 = 0;

    @Autowired
    TicketRespository repo;

    private List<Ticket> ticketList = new ArrayList<>();


    @Override
    public Ticket createTicket(Ticket ticket) {
        return repo.create(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public boolean update(Long id, Ticket ticket) {
        if (!checkIfStatusIsTrue(id)) {
            return repo.update(id, ticket);
        }
        else {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        return repo.delete(id);
    }

    private boolean checkIfStatusIsTrue(Long id) {
        if (findById(id).isPresent()) {
            Optional<Ticket> ticket = findById(id);
            if (ticket.get().isChecked()) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    public List<Ticket> runResultChecking() {
        ticketList = findAll();
        for (Ticket ticket : ticketList) {
            List<Line> lineList = ticket.getLines();
            for (Line line : lineList) {
                while (true) {
                    if ((line.getNumber_1() + line.getNumber_2() + line.getNumber_3() == 2)) {
                        line.setResult(RESULT_10);
                        update(ticket.getId(), ticket);
                        break;
                    }
                    if ((line.getNumber_1() == line.getNumber_2()) && (line.getNumber_2() == line.getNumber_3())) {
                        line.setResult(RESULT_5);
                        update(ticket.getId(), ticket);
                        break;
                    }
                    if (line.getNumber_2() == line.getNumber_3() && line.getNumber_1() != line.getNumber_3()) {
                        line.setResult(RESULT_1);
                        update(ticket.getId(), ticket);
                        break;
                    } else {
                        line.setResult(RESULT_0);
                        update(ticket.getId(), ticket);
                        break;
                    }
                }
            }
            repo.update(ticket.getId(), ticket, true);
        }
        return ticketList;
    }
}
