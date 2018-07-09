package com.task.lotterydemo.resource;

import com.task.lotterydemo.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class TicketResourceAssembler extends ResourceAssembler<Ticket, TicketResource> {

    @Autowired
    protected EntityLinks entityLinks;

    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    @Override
    public TicketResource toResource(Ticket ticket) {
        TicketResource resource = new TicketResource(ticket);
        final Link link = entityLinks.linkToSingleResource(ticket);

        resource.add(link.withSelfRel());
        resource.add(link.withRel(UPDATE));
        resource.add(link.withRel(DELETE));

        return resource;
    }
}
