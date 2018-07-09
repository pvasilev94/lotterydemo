package com.task.lotterydemo.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.lotterydemo.domain.Line;
import com.task.lotterydemo.domain.Ticket;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class TicketResource extends ResourceSupport {

    private final long id;
    private final List<Line> lines;
    private final boolean isChecked;
    private final int modified;

    public TicketResource(Ticket ticket) {
        id = ticket.getId();
        lines = ticket.getLines();
        isChecked = ticket.isChecked();
        modified = ticket.getModified();
    }

    @JsonProperty("id")
    public long getResourceId() {
        return id;
    }

    public List<Line> getLinesList() {
        return lines;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public int getModified() { return modified;
    }
}
