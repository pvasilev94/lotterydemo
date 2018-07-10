package com.task.lotterydemo.Controller;

import com.task.lotterydemo.domain.Ticket;
import com.task.lotterydemo.resource.TicketResource;
import com.task.lotterydemo.resource.TicketResourceAssembler;
import com.task.lotterydemo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Ticket.class)
@RequestMapping(value = "/ticket", produces = "application/json")
public class TicketController {

    @Autowired
    private TicketService service;

    @Autowired
    private TicketResourceAssembler assembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<TicketResource>> findAllOrders() {
        List<Ticket> tickets = service.findAll();
        return new ResponseEntity<>(assembler.toResourceCollection(tickets), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<TicketResource> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = service.createTicket(ticket);
        return new ResponseEntity<>(assembler.toResource(createdTicket), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TicketResource> findTicketById(@PathVariable Long id) {
        Optional<Ticket> ticket = service.findById(id);
        if (ticket.isPresent()) {
            return new ResponseEntity<>(assembler.toResource(ticket.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        boolean isDeleted = service.delete(id);
        HttpStatus responseStatus = isDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<TicketResource> updateTicket(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
        boolean isUpdated = service.update(id, updatedTicket);
        if (service.findById(id).isPresent()) {
            if (isUpdated) {
                return findTicketById(id);
            } else {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<Collection<TicketResource>> runResult() {
        List<Ticket> tickets = service.runResultChecking();
        if (tickets != null) {
            return new ResponseEntity<>(assembler.toResourceCollection(tickets), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

