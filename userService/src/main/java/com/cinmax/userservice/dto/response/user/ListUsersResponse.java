package com.cinmax.userservice.dto.response.user;

import com.cinmax.userservice.model.User;
import java.util.List;

public class ListUsersResponse {
    private List<User> users;

    public ListUsersResponse() {}

    public ListUsersResponse(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}