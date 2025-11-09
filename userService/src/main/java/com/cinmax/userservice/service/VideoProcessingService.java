package com.cinmax.userservice.service;

import com.cinmax.userservice.model.Movie;
import com.cinmax.userservice.model.StreamQuality;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoProcessingService {

    private final MovieAnalysisService movieAnalysisService;

    public VideoProcessingService() {
        this.movieAnalysisService = new MovieAnalysisService();
    }

    // In a real implementation, this would use FFmpeg to process videos
    // For now, we'll simulate the processing and generate mock HLS data

    public void processVideoForStreaming(Movie movie) {
        try {
            // Assume video file is stored locally (adjust path as needed)
            String inputVideoPath = getLocalVideoPath(movie);
            String outputBaseDir = "processed_videos/" + movie.getId() + "/";

            // Create output directory
            Files.createDirectories(Paths.get(outputBaseDir));

            // Generate master playlist URL
            String baseUrl = "https://cdn.example.com/movies/" + movie.getId() + "/";
            movie.setMasterPlaylistUrl(baseUrl + "master.m3u8");

            // Define quality renditions
            List<StreamQuality> qualities = Arrays.asList(
                new StreamQuality("1080p", 5000000L, baseUrl + "1080p/playlist.m3u8", 1920, 1080),
                new StreamQuality("720p", 2800000L, baseUrl + "720p/playlist.m3u8", 1280, 720),
                new StreamQuality("480p", 1400000L, baseUrl + "480p/playlist.m3u8", 854, 480),
                new StreamQuality("360p", 800000L, baseUrl + "360p/playlist.m3u8", 640, 360)
            );

            // Process each quality
            for (StreamQuality quality : qualities) {
                transcodeToQuality(inputVideoPath, outputBaseDir, quality);
            }

            movie.setQualities(qualities);
            movie.setProcessedForStreaming(true);

            // Perform AI analysis for cinematic and story quality
            movieAnalysisService.analyzeMovie(movie);

        } catch (Exception e) {
            System.err.println("Error processing video for streaming: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Video processing failed", e);
        }
    }

    /**
     * Get the local path to the video file
     * In production, this might download from S3 or access from a shared storage
     */
    private String getLocalVideoPath(Movie movie) {
        // For now, assume videos are stored in a local directory
        // In production, you might need to download from S3 first
        return "uploaded_videos/" + movie.getFileName();
    }

    /**
     * Transcode video to a specific quality using FFmpeg
     */
    private void transcodeToQuality(String inputPath, String outputBaseDir, StreamQuality quality) throws IOException, InterruptedException {
        String qualityDir = outputBaseDir + quality.getResolution() + "/";
        Files.createDirectories(Paths.get(qualityDir));

        String outputPlaylist = qualityDir + "playlist.m3u8";
        String outputSegmentPattern = qualityDir + "segment_%03d.ts";

        // FFmpeg command for HLS transcoding
        String[] ffmpegCommand = {
            "ffmpeg",
            "-i", inputPath,                    // Input file
            "-vf", "scale=" + quality.getWidth() + ":" + quality.getHeight(), // Scale filter
            "-c:v", "libx264",                  // Video codec
            "-b:v", quality.getBandwidth() + "", // Video bitrate
            "-c:a", "aac",                      // Audio codec
            "-b:a", "128k",                     // Audio bitrate
            "-ac", "2",                         // Stereo audio
            "-f", "hls",                        // HLS format
            "-hls_time", "10",                  // Segment duration (10 seconds)
            "-hls_list_size", "0",              // Keep all segments in playlist
            "-hls_segment_filename", outputSegmentPattern, // Segment naming pattern
            "-y",                               // Overwrite output files
            outputPlaylist                       // Output playlist
        };

        // Execute FFmpeg command
        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand);
        processBuilder.redirectErrorStream(true); // Merge stdout and stderr

        System.out.println("Starting FFmpeg transcoding for " + quality.getResolution() + "...");
        Process process = processBuilder.start();

        // Wait for completion
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("FFmpeg transcoding failed for " + quality.getResolution() + " with exit code " + exitCode);
        }

        System.out.println("Completed transcoding for " + quality.getResolution());
    }

    /**
     * Generates a master HLS playlist content
     * In production, this would be stored on CDN/storage
     */
    public String generateMasterPlaylist(Movie movie) {
        StringBuilder playlist = new StringBuilder();
        playlist.append("#EXTM3U\n");
        playlist.append("#EXT-X-VERSION:3\n");

        for (StreamQuality quality : movie.getQualities()) {
            playlist.append(String.format("#EXT-X-STREAM-INF:BANDWIDTH=%d,RESOLUTION=%dx%d\n",
                quality.getBandwidth(), quality.getWidth(), quality.getHeight()));
            playlist.append(getRelativePlaylistUrl(quality.getPlaylistUrl(), movie.getId())).append("\n");
        }

        return playlist.toString();
    }

    /**
     * Generates a quality-specific HLS playlist content
     * In production, this would be stored on CDN/storage
     */
    public String generateQualityPlaylist(Movie movie, StreamQuality quality) {
        try {
            // Read the actual generated playlist file
            String playlistPath = "processed_videos/" + movie.getId() + "/" + quality.getResolution() + "/playlist.m3u8";
            return new String(Files.readAllBytes(Paths.get(playlistPath)));
        } catch (IOException e) {
            // Fallback to mock content if file doesn't exist
            System.err.println("Could not read playlist file: " + e.getMessage());
            return generateMockPlaylist();
        }
    }

    /**
     * Fallback mock playlist generation
     */
    private String generateMockPlaylist() {
        StringBuilder playlist = new StringBuilder();
        playlist.append("#EXTM3U\n");
        playlist.append("#EXT-X-VERSION:3\n");
        playlist.append("#EXT-X-TARGETDURATION:10\n");
        playlist.append("#EXT-X-MEDIA-SEQUENCE:0\n");

        // Simulate 10-second segments (in reality, FFmpeg would create actual segments)
        int segmentCount = 30; // Assume 5-minute video = 30 segments
        for (int i = 0; i < segmentCount; i++) {
            playlist.append(String.format("#EXTINF:10.0,\n"));
            playlist.append(String.format("segment_%d.ts\n", i));
        }

        playlist.append("#EXT-X-ENDLIST\n");
        return playlist.toString();
    }

    private String getRelativePlaylistUrl(String fullUrl, Long movieId) {
        // Extract relative path from full URL
        return fullUrl.substring(fullUrl.lastIndexOf("/") + 1);
    }

}