package com.cinmax.userservice.dto.request.ticket;

public class CreateTicketRequest {
    private Long userId;
    private String movieName;

    public CreateTicketRequest() {}

    public CreateTicketRequest(Long userId, String movieName) {
        this.userId = userId;
        this.movieName = movieName;
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