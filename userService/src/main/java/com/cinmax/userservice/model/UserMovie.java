package com.cinmax.userservice.model;

import java.time.LocalDateTime;

public class UserMovie {
    private Long userId;
    private String ticketId;
    private Long movieId;
    private String rentalType; // "buy" or "rent"
    private Integer rentalDuration; // in hours, null if buy
    private LocalDateTime purchaseDate;
    private LocalDateTime expiryDate; // for rent

    public UserMovie() {}

    public UserMovie(Long userId, String ticketId, Long movieId, String rentalType, Integer rentalDuration, LocalDateTime purchaseDate, LocalDateTime expiryDate) {
        this.userId = userId;
        this.ticketId = ticketId;
        this.movieId = movieId;
        this.rentalType = rentalType;
        this.rentalDuration = rentalDuration;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}