package com.cinmax.userservice.dto.response.ticket;

import com.cinmax.userservice.model.Ticket;

public class TicketResponse {
    private Ticket ticket;

    public TicketResponse() {}

    public TicketResponse(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}