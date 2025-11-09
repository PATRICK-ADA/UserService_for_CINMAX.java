package com.cinmax.userservice.repository;

import com.cinmax.userservice.model.Payment;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class PaymentRepository {
    private final Connection connection;

    public PaymentRepository(Connection connection) {
        this.connection = connection;
    }

    public Payment save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (payment_id, user_id, movie_id, amount, status, payment_date, ticket_id, rental_type, rental_duration) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, payment.getPaymentId());
            stmt.setLong(2, payment.getUserId());
            stmt.setLong(3, payment.getMovieId());
            stmt.setDouble(4, payment.getAmount());
            stmt.setString(5, payment.getStatus());
            stmt.setTimestamp(6, Timestamp.valueOf(payment.getPaymentDate()));
            stmt.setString(7, payment.getTicketId());
            stmt.setString(8, payment.getRentalType());
            if (payment.getRentalDuration() != null) {
                stmt.setInt(9, payment.getRentalDuration());
            } else {
                stmt.setNull(9, Types.INTEGER);
            }
            stmt.executeUpdate();
        }
        return payment;
    }

    public List<Payment> findByUserId(Long userId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT payment_id, user_id, movie_id, amount, status, payment_date, ticket_id, rental_type, rental_duration FROM payments WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("payment_id"));
                payment.setUserId(rs.getLong("user_id"));
                payment.setMovieId(rs.getLong("movie_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setStatus(rs.getString("status"));
                payment.setPaymentDate(rs.getTimestamp("payment_date").toLocalDateTime());
                payment.setTicketId(rs.getString("ticket_id"));
                payment.setRentalType(rs.getString("rental_type"));
                int rentalDuration = rs.getInt("rental_duration");
                if (!rs.wasNull()) {
                    payment.setRentalDuration(rentalDuration);
                }
                payments.add(payment);
            }
        }
        return payments;
    }

    public Payment findByPaymentId(String paymentId) throws SQLException {
        String sql = "SELECT payment_id, user_id, movie_id, amount, status, payment_date, ticket_id, rental_type, rental_duration FROM payments WHERE payment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, paymentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("payment_id"));
                payment.setUserId(rs.getLong("user_id"));
                payment.setMovieId(rs.getLong("movie_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setStatus(rs.getString("status"));
                payment.setPaymentDate(rs.getTimestamp("payment_date").toLocalDateTime());
                payment.setTicketId(rs.getString("ticket_id"));
                payment.setRentalType(rs.getString("rental_type"));
                int rentalDuration = rs.getInt("rental_duration");
                if (!rs.wasNull()) {
                    payment.setRentalDuration(rentalDuration);
                }
                return payment;
            }
        }
        return null;
    }
}