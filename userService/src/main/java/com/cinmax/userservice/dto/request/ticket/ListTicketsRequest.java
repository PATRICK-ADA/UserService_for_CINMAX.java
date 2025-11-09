package com.cinmax.userservice.dto.request.ticket;

public class ListTicketsRequest {
    private Long userId;

    public ListTicketsRequest() {}

    public ListTicketsRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}