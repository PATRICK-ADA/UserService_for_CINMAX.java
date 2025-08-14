package com.example.userservice.repository;

import com.example.userservice.model.Ticket;
import java.sql.*;
import java.util.*;

public class TicketRepository {
    private final Connection connection;

    public TicketRepository(Connection connection) {
        this.connection = connection;
    }

    public Ticket save(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO tickets (ticket_id, user_id, movie_name) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ticket.getTicketId());
            stmt.setLong(2, ticket.getUserId());
            stmt.setString(3, ticket.getMovieName());
            stmt.executeUpdate();
        }
        return ticket;
    }

    public List<Ticket> findByUserId(Long userId) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT ticket_id, user_id, movie_name FROM tickets WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tickets.add(new Ticket(rs.getString("ticket_id"), rs.getLong("user_id"), rs.getString("movie_name")));
            }
        }
        return tickets;
    }
}
