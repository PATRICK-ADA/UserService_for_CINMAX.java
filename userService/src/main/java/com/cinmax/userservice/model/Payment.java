package com.cinmax.userservice.model;

import java.time.LocalDateTime;

public class Payment {
    private String paymentId;
    private Long userId;
    private Long movieId;
    private double amount;
    private String status; // "pending", "completed", "failed"
    private LocalDateTime paymentDate;
    private String ticketId;
    private String rentalType;
    private Integer rentalDuration;

    public Payment() {}

    public Payment(String paymentId, Long userId, Long movieId, double amount, String status, LocalDateTime paymentDate, String ticketId, String rentalType, Integer rentalDuration) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.movieId = movieId;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
        this.ticketId = ticketId;
        this.rentalType = rentalType;
        this.rentalDuration = rentalDuration;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
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
}