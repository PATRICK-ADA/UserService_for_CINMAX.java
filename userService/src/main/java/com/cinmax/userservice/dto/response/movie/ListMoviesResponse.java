package com.cinmax.userservice.dto.response.movie;

import com.cinmax.userservice.model.Movie;
import java.util.List;

public class ListMoviesResponse {
    private List<Movie> movies;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    public ListMoviesResponse() {}

    public ListMoviesResponse(List<Movie> movies, int currentPage, int pageSize, int totalPages, long totalElements) {
        this.movies = movies;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}