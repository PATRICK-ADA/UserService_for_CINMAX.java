package com.example.userservice.model;

public class Ticket {
    private String ticketId;
    private Long userId;
    private String movieName;

    public Ticket() {}

    public Ticket(String ticketId, Long userId, String movieName) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.movieName = movieName;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
