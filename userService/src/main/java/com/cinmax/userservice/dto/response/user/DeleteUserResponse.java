package com.cinmax.userservice.dto.response.user;

public class DeleteUserResponse {
    private boolean success;

    public DeleteUserResponse() {}

    public DeleteUserResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}