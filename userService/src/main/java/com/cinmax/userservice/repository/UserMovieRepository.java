package com.cinmax.userservice.repository;

import com.cinmax.userservice.model.UserMovie;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class UserMovieRepository {
    private final Connection connection;

    public UserMovieRepository(Connection connection) {
        this.connection = connection;
    }

    public UserMovie save(UserMovie userMovie) throws SQLException {
        String sql = "INSERT INTO user_movies (user_id, ticket_id, movie_id, rental_type, rental_duration, purchase_date, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userMovie.getUserId());
            stmt.setString(2, userMovie.getTicketId());
            stmt.setLong(3, userMovie.getMovieId());
            stmt.setString(4, userMovie.getRentalType());
            if (userMovie.getRentalDuration() != null) {
                stmt.setInt(5, userMovie.getRentalDuration());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setTimestamp(6, Timestamp.valueOf(userMovie.getPurchaseDate()));
            if (userMovie.getExpiryDate() != null) {
                stmt.setTimestamp(7, Timestamp.valueOf(userMovie.getExpiryDate()));
            } else {
                stmt.setNull(7, Types.TIMESTAMP);
            }
            stmt.executeUpdate();
        }
        return userMovie;
    }

    public List<UserMovie> findByUserId(Long userId) throws SQLException {
        List<UserMovie> userMovies = new ArrayList<>();
        String sql = "SELECT user_id, ticket_id, movie_id, rental_type, rental_duration, purchase_date, expiry_date FROM user_movies WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserMovie userMovie = new UserMovie();
                userMovie.setUserId(rs.getLong("user_id"));
                userMovie.setTicketId(rs.getString("ticket_id"));
                userMovie.setMovieId(rs.getLong("movie_id"));
                userMovie.setRentalType(rs.getString("rental_type"));
                int rentalDuration = rs.getInt("rental_duration");
                if (!rs.wasNull()) {
                    userMovie.setRentalDuration(rentalDuration);
                }
                userMovie.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
                Timestamp expiryTs = rs.getTimestamp("expiry_date");
                if (expiryTs != null) {
                    userMovie.setExpiryDate(expiryTs.toLocalDateTime());
                }
                userMovies.add(userMovie);
            }
        }
        return userMovies;
    }

    public UserMovie findByTicketId(String ticketId) throws SQLException {
        String sql = "SELECT user_id, ticket_id, movie_id, rental_type, rental_duration, purchase_date, expiry_date FROM user_movies WHERE ticket_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ticketId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UserMovie userMovie = new UserMovie();
                userMovie.setUserId(rs.getLong("user_id"));
                userMovie.setTicketId(rs.getString("ticket_id"));
                userMovie.setMovieId(rs.getLong("movie_id"));
                userMovie.setRentalType(rs.getString("rental_type"));
                int rentalDuration = rs.getInt("rental_duration");
                if (!rs.wasNull()) {
                    userMovie.setRentalDuration(rentalDuration);
                }
                userMovie.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
                Timestamp expiryTs = rs.getTimestamp("expiry_date");
                if (expiryTs != null) {
                    userMovie.setExpiryDate(expiryTs.toLocalDateTime());
                }
                return userMovie;
            }
        }
        return null;
    }
}