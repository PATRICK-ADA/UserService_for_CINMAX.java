package com.cinmax.userservice.dto.response.ticket;

import com.cinmax.userservice.model.Ticket;
import java.util.List;

public class ListTicketsResponse {
    private List<Ticket> tickets;

    public ListTicketsResponse() {}

    public ListTicketsResponse(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}