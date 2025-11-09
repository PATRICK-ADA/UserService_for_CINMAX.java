package com.cinmax.userservice.dto.response.movie;

import com.cinmax.userservice.model.Movie;

public class GetMovieResponse {
    private Movie movie;

    public GetMovieResponse() {}

    public GetMovieResponse(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}