package com.cinmax.userservice.dto.request.movie;

public class ListMoviesRequest {
    private int page = 1;
    private int size = 10;

    public ListMoviesRequest() {}

    public ListMoviesRequest(int page, int size) {
        this.page = page > 0 ? page : 1;
        this.size = size > 0 ? size : 10;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page > 0 ? page : 1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size > 0 ? size : 10;
    }
}