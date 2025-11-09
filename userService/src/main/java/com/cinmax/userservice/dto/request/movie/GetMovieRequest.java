package com.cinmax.userservice.dto.request.movie;

public class GetMovieRequest {
    private Long movieId;

    public GetMovieRequest() {}

    public GetMovieRequest(Long movieId) {
        this.movieId = movieId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}