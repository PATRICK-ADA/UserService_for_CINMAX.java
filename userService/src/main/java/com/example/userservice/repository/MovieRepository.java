package com.example.userservice.repository;

import com.example.userservice.model.Movie;
import com.example.userservice.model.StreamQuality;
import java.sql.*;
import java.util.*;

public class MovieRepository {
    private final Connection connection;

    public MovieRepository(Connection connection) {
        this.connection = connection;
    }

    public Movie save(Movie movie) throws SQLException {
        String sql = "INSERT INTO movies (title, description, file_data, file_name, content_type, master_playlist_url, processed_for_streaming) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDescription());
            stmt.setBytes(3, movie.getFileData());
            stmt.setString(4, movie.getFileName());
            stmt.setString(5, movie.getContentType());
            stmt.setString(6, movie.getMasterPlaylistUrl());
            stmt.setBoolean(7, movie.isProcessedForStreaming());
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
        String sql = "SELECT id, title, description, file_data, file_name, content_type, master_playlist_url, processed_for_streaming FROM movies";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getBytes("file_data"),
                    rs.getString("file_name"),
                    rs.getString("content_type")
                );
                movie.setMasterPlaylistUrl(rs.getString("master_playlist_url"));
                movie.setProcessedForStreaming(rs.getBoolean("processed_for_streaming"));

                // Load qualities
                movie.setQualities(findQualitiesByMovieId(movie.getId()));

                movies.add(movie);
            }
        }
        return movies;
    }

    public Movie findById(Long id) throws SQLException {
        String sql = "SELECT id, title, description, file_data, file_name, content_type, master_playlist_url, processed_for_streaming FROM movies WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Movie movie = new Movie(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getBytes("file_data"),
                    rs.getString("file_name"),
                    rs.getString("content_type")
                );
                movie.setMasterPlaylistUrl(rs.getString("master_playlist_url"));
                movie.setProcessedForStreaming(rs.getBoolean("processed_for_streaming"));
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