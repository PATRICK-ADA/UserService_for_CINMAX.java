package com.cinmax.userservice.dto.request.user;

public class GetUserRequest {
    private Long id;

    public GetUserRequest() {}

    public GetUserRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}