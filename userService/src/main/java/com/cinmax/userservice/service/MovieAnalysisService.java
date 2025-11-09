package com.cinmax.userservice.service;

import com.cinmax.userservice.model.Movie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieAnalysisService {

    private final HttpClient httpClient;

    // Replace with your actual API key and endpoint
    private static final String OPENROUTER_API_KEY = "your-openrouter-api-key";
    private static final String OPENROUTER_URL = "https://openrouter.ai/api/v1/chat/completions";

    public MovieAnalysisService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Extracts video metadata using FFmpeg
     */
    public void extractVideoMetadata(Movie movie) {
        try {
            // Assume the video file is accessible via S3 URL or local path
            // In production, you might need to download the file first
            String videoPath = movie.getS3Url(); // This might need adjustment

            ProcessBuilder processBuilder = new ProcessBuilder(
                "ffprobe", "-v", "quiet", "-print_format", "json", "-show_format", "-show_streams", videoPath
            );

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            process.waitFor();

            // Parse the JSON output (simplified parsing)
            parseFFprobeOutput(output.toString(), movie);

        } catch (Exception e) {
            // If FFmpeg fails, use fallback or manual metadata
            System.out.println("FFmpeg metadata extraction failed: " + e.getMessage());
        }
    }

    /**
     * Parses FFprobe JSON output to extract metadata
     */
    private void parseFFprobeOutput(String jsonOutput, Movie movie) {
        try {
            // Simple regex-based parsing (in production, use a JSON library)
            Pattern widthPattern = Pattern.compile("\"width\"\\s*:\\s*(\\d+)");
            Pattern heightPattern = Pattern.compile("\"height\"\\s*:\\s*(\\d+)");
            Pattern fpsPattern = Pattern.compile("\"r_frame_rate\"\\s*:\\s*\"([^\"]+)\"");

            Matcher widthMatcher = widthPattern.matcher(jsonOutput);
            if (widthMatcher.find()) {
                movie.setResolutionWidth(Integer.parseInt(widthMatcher.group(1)));
            }

            Matcher heightMatcher = heightPattern.matcher(jsonOutput);
            if (heightMatcher.find()) {
                movie.setResolutionHeight(Integer.parseInt(heightMatcher.group(1)));
            }

            Matcher fpsMatcher = fpsPattern.matcher(jsonOutput);
            if (fpsMatcher.find()) {
                String fpsStr = fpsMatcher.group(1);
                // Parse frame rate like "30/1" to 30.0
                if (fpsStr.contains("/")) {
                    String[] parts = fpsStr.split("/");
                    if (parts.length == 2) {
                        double num = Double.parseDouble(parts[0]);
                        double den = Double.parseDouble(parts[1]);
                        movie.setFrameRate(num / den);
                    }
                }
            }

            // Set sound quality based on audio streams (simplified)
            if (jsonOutput.contains("\"codec_type\"\\s*:\\s*\"audio\"")) {
                if (jsonOutput.contains("\"channel_layout\"\\s*:\\s*\"stereo\"")) {
                    movie.setSoundQuality("STEREO");
                } else if (jsonOutput.contains("\"channels\"\\s*:\\s*6") || jsonOutput.contains("\"channel_layout\"\\s*:\\s*\"5.1\"")) {
                    movie.setSoundQuality("SURROUND");
                } else {
                    movie.setSoundQuality("MONO");
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to parse FFprobe output: " + e.getMessage());
        }
    }

    /**
     * Analyzes cinematic quality based on resolution, frame rate, color grading, sound quality, cinematography
     * Uses AI to analyze video metadata and content
     */
    public double analyzeCinematicQuality(Movie movie) {
        // For now, simulate analysis based on metadata
        // In production, this would call AI services like AWS Rekognition or Google Cloud Video AI

        double score = 0.0;

        // Resolution check (4K/2K = 5 points, HD = 3 points, lower = 0)
        if (movie.getResolutionWidth() != null && movie.getResolutionHeight() != null) {
            if (movie.getResolutionWidth() >= 3840 || movie.getResolutionHeight() >= 2160) {
                score += 5.0; // 4K
            } else if (movie.getResolutionWidth() >= 1920 || movie.getResolutionHeight() >= 1080) {
                score += 3.0; // HD
            }
        }

        // Frame rate check (24/25/30fps = 5 points, others = 0)
        if (movie.getFrameRate() != null) {
            if (movie.getFrameRate() == 24 || movie.getFrameRate() == 25 || movie.getFrameRate() == 30) {
                score += 5.0;
            }
        }

        // Color grading (CINEMATIC = 5, STANDARD = 3, RAW = 0)
        if ("CINEMATIC".equals(movie.getColorGrading())) {
            score += 5.0;
        } else if ("STANDARD".equals(movie.getColorGrading())) {
            score += 3.0;
        }

        // Sound quality (SURROUND = 5, STEREO = 4, MONO = 0)
        if ("SURROUND".equals(movie.getSoundQuality())) {
            score += 5.0;
        } else if ("STEREO".equals(movie.getSoundQuality())) {
            score += 4.0;
        }

        // Cinematography - placeholder (would need AI analysis of video content)
        // For now, assume basic analysis
        score += 3.0; // Placeholder

        // Normalize to 0-5 scale
        return Math.min(5.0, score / 5.0 * 5.0);
    }

    /**
     * Analyzes story quality based on synopsis, viewer ratings, watch-through rate
     * Uses AI (like GPT-4) to analyze narrative structure
     */
    public double analyzeStoryQuality(Movie movie) {
        double score = 0.0;

        // Analyze synopsis with AI
        if (movie.getSynopsis() != null && !movie.getSynopsis().isEmpty()) {
            score += analyzeSynopsisWithAI(movie.getSynopsis());
        }

        // Viewer rating contribution (scale 0-5)
        if (movie.getViewerRating() != null) {
            score += movie.getViewerRating() / 2.0; // Convert 0-10 scale to 0-5
        }

        // Watch-through rate (higher completion = better story)
        if (movie.getWatchThroughRate() != null) {
            if (movie.getWatchThroughRate() > 0.8) score += 5.0;
            else if (movie.getWatchThroughRate() > 0.6) score += 3.0;
            else if (movie.getWatchThroughRate() > 0.4) score += 2.0;
        }

        return Math.min(5.0, score);
    }

    /**
     * Uses AI to analyze synopsis for story quality criteria
     */
    private double analyzeSynopsisWithAI(String synopsis) {
        try {
            // Prepare the prompt for story analysis
            String prompt = "Analyze this movie synopsis for story quality. Rate it on a scale of 0-5 based on: " +
                    "plot coherence, character development, conflict & resolution, dialogue quality, theme depth, pacing balance. " +
                    "Return only a number between 0 and 5.\n\nSynopsis: " + synopsis;

            // Create JSON request body
            String jsonBody = String.format(
                "{\"model\":\"openai/gpt-4o-mini\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}]}",
                prompt.replace("\"", "\\\"").replace("\n", "\\n")
            );

            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OPENROUTER_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + OPENROUTER_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

            // Send request
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Simple JSON parsing - extract the number from response
                String body = response.body();
                // Look for a number in the response (simplified parsing)
                String content = extractContentFromResponse(body);
                if (content != null) {
                    try {
                        return Double.parseDouble(content.trim());
                    } catch (NumberFormatException e) {
                        // Continue to fallback
                    }
                }
            }
        } catch (Exception e) {
            // Fallback to basic analysis
            return calculateBasicStoryScore(synopsis);
        }

        return calculateBasicStoryScore(synopsis);
    }

    /**
     * Simple method to extract content from OpenRouter API response
     */
    private String extractContentFromResponse(String jsonResponse) {
        // Very basic JSON parsing - look for "content":" followed by a number
        int contentIndex = jsonResponse.indexOf("\"content\":\"");
        if (contentIndex != -1) {
            int start = contentIndex + 11; // length of "content":"
            int end = jsonResponse.indexOf("\"", start);
            if (end != -1) {
                return jsonResponse.substring(start, end);
            }
        }
        return null;
    }

    /**
     * Basic story analysis without AI (keyword matching)
     */
    private double calculateBasicStoryScore(String synopsis) {
        String lowerSynopsis = synopsis.toLowerCase();
        double score = 0.0;

        // Check for story elements
        if (lowerSynopsis.contains("conflict") || lowerSynopsis.contains("problem") || lowerSynopsis.contains("challenge")) score += 1.0;
        if (lowerSynopsis.contains("resolution") || lowerSynopsis.contains("solve") || lowerSynopsis.contains("end")) score += 1.0;
        if (lowerSynopsis.contains("character") || lowerSynopsis.contains("hero") || lowerSynopsis.contains("protagonist")) score += 1.0;
        if (lowerSynopsis.contains("dialogue") || lowerSynopsis.contains("conversation")) score += 1.0;
        if (lowerSynopsis.contains("theme") || lowerSynopsis.contains("message") || lowerSynopsis.contains("moral")) score += 1.0;
        if (lowerSynopsis.contains("pacing") || lowerSynopsis.contains("rhythm") || lowerSynopsis.contains("tempo")) score += 1.0;

        return Math.min(5.0, score);
    }

    /**
     * Calculates overall rating: cinematic (1.0) + story (0.5) = 1.5 max
     */
    public double calculateOverallRating(Movie movie) {
        double cinematic = movie.getCinematicQualityScore();
        double story = movie.getStoryQualityScore();
        return cinematic + (story * 0.5);
    }

    /**
     * Determines pricing tier based on overall rating
     */
    public String determinePricingTier(Movie movie) {
        double rating = movie.getOverallRating();

        if (rating >= 9.0) return "PREMIUM"; // 1000
        else if (rating >= 7.0) return "STANDARD"; // 500
        else if (rating >= 5.0) return "BASIC"; // 300
        else return "ECONOMY"; // 200
    }

    /**
     * Sets pricing based on tier
     */
    public void setPricingBasedOnTier(Movie movie) {
        String tier = movie.getPricingTier();
        switch (tier) {
            case "PREMIUM":
                movie.setBuyPrice(1000.0);
                movie.setRentalPrice(100.0);
                break;
            case "STANDARD":
                movie.setBuyPrice(500.0);
                movie.setRentalPrice(50.0);
                break;
            case "BASIC":
                movie.setBuyPrice(300.0);
                movie.setRentalPrice(30.0);
                break;
            case "ECONOMY":
            default:
                movie.setBuyPrice(200.0);
                movie.setRentalPrice(20.0);
                break;
        }
    }

    /**
     * Performs complete analysis of a movie
     */
    public void analyzeMovie(Movie movie) {
        // First extract metadata from video file
        extractVideoMetadata(movie);

        // Then analyze quality aspects
        movie.setCinematicQualityScore(analyzeCinematicQuality(movie));
        movie.setStoryQualityScore(analyzeStoryQuality(movie));
        movie.setOverallRating(calculateOverallRating(movie));
        movie.setPricingTier(determinePricingTier(movie));
        setPricingBasedOnTier(movie);
    }
}