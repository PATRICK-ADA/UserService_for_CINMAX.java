package com.cinmax.userservice.repository;

import com.cinmax.userservice.model.Movie;
import com.cinmax.userservice.model.StreamQuality;
import java.sql.*;
import java.util.*;

public class MovieRepository {
    private final Connection connection;

    public MovieRepository(Connection connection) {
        this.connection = connection;
    }

    public Movie save(Movie movie) throws SQLException {
        String sql = "INSERT INTO movies (title, description, s3_url, file_name, content_type, file_size, master_playlist_url, processed_for_streaming, buy_price, rental_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getS3Url());
            stmt.setString(4, movie.getFileName());
            stmt.setString(5, movie.getContentType());
            stmt.setLong(6, movie.getFileSize());
            stmt.setString(7, movie.getMasterPlaylistUrl());
            stmt.setBoolean(8, movie.isProcessedForStreaming());
            stmt.setDouble(9, movie.getBuyPrice());
            stmt.setDouble(10, movie.getRentalPrice());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                movie.setId(rs.getLong(1));
            }
        }

        // Save qualities if any
        if (movie.getQualities() != null && !movie.getQualities().isEmpty()) {
            saveQualities(movie.getId(), movie.getQualities());
        }

        return movie;
    }

    public Movie updateStreamingInfo(Long movieId, String masterPlaylistUrl, List<StreamQuality> qualities) throws SQLException {
        String sql = "UPDATE movies SET master_playlist_url = ?, processed_for_streaming = 1 WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, masterPlaylistUrl);
            stmt.setLong(2, movieId);
            stmt.executeUpdate();
        }

        // Save qualities
        if (qualities != null && !qualities.isEmpty()) {
            saveQualities(movieId, qualities);
        }

        return findById(movieId);
    }

    private void saveQualities(Long movieId, List<StreamQuality> qualities) throws SQLException {
        String sql = "INSERT INTO movie_qualities (movie_id, resolution, bandwidth, playlist_url, width, height) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (StreamQuality quality : qualities) {
                stmt.setLong(1, movieId);
                stmt.setString(2, quality.getResolution());
                stmt.setLong(3, quality.getBandwidth());
                stmt.setString(4, quality.getPlaylistUrl());
                stmt.setInt(5, quality.getWidth());
                stmt.setInt(6, quality.getHeight());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public List<Movie> findAll() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT id, title, description, s3_url, file_name, content_type, file_size, master_playlist_url, processed_for_streaming, buy_price, rental_price FROM movies";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("s3_url"),
                    rs.getString("file_name"),
                    rs.getString("content_type"),
                    rs.getLong("file_size")
                );
                movie.setMasterPlaylistUrl(rs.getString("master_playlist_url"));
                movie.setProcessedForStreaming(rs.getBoolean("processed_for_streaming"));
                movie.setBuyPrice(rs.getDouble("buy_price"));
                movie.setRentalPrice(rs.getDouble("rental_price"));

                // Load qualities
                movie.setQualities(findQualitiesByMovieId(movie.getId()));

                movies.add(movie);
            }
        }
        return movies;
    }

    public List<Movie> findAllPaginated(int page, int size) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT id, title, description, s3_url, file_name, content_type, file_size, master_playlist_url, processed_for_streaming, buy_price, rental_price FROM movies ORDER BY id LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, size);
            stmt.setInt(2, (page - 1) * size);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("s3_url"),
                    rs.getString("file_name"),
                    rs.getString("content_type"),
                    rs.getLong("file_size")
                );
                movie.setMasterPlaylistUrl(rs.getString("master_playlist_url"));
                movie.setProcessedForStreaming(rs.getBoolean("processed_for_streaming"));
                movie.setBuyPrice(rs.getDouble("buy_price"));
                movie.setRentalPrice(rs.getDouble("rental_price"));

                // Load qualities
                movie.setQualities(findQualitiesByMovieId(movie.getId()));

                movies.add(movie);
            }
        }
        return movies;
    }

    public long countAll() throws SQLException {
        String sql = "SELECT COUNT(*) FROM movies";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        }
        return 0;
    }

    public Movie findById(Long id) throws SQLException {
        String sql = "SELECT id, title, description, s3_url, file_name, content_type, file_size, master_playlist_url, processed_for_streaming, buy_price, rental_price FROM movies WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Movie movie = new Movie(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("s3_url"),
                    rs.getString("file_name"),
                    rs.getString("content_type"),
                    rs.getLong("file_size")
                );
                movie.setMasterPlaylistUrl(rs.getString("master_playlist_url"));
                movie.setProcessedForStreaming(rs.getBoolean("processed_for_streaming"));
                movie.setBuyPrice(rs.getDouble("buy_price"));
                movie.setRentalPrice(rs.getDouble("rental_price"));
                movie.setQualities(findQualitiesByMovieId(id));
                return movie;
            }
        }
        return null;
    }

    private List<StreamQuality> findQualitiesByMovieId(Long movieId) throws SQLException {
        List<StreamQuality> qualities = new ArrayList<>();
        String sql = "SELECT resolution, bandwidth, playlist_url, width, height FROM movie_qualities WHERE movie_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, movieId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                qualities.add(new StreamQuality(
                    rs.getString("resolution"),
                    rs.getLong("bandwidth"),
                    rs.getString("playlist_url"),
                    rs.getInt("width"),
                    rs.getInt("height")
                ));
            }
        }
        return qualities;
    }
}