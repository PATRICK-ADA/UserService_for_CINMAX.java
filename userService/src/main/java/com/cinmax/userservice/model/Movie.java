package com.cinmax.userservice.model;

import java.util.List;
import java.util.ArrayList;

public class Movie {
    private Long id;
    private String title;
    private String description;
    private String s3Url;
    private String fileName;
    private String contentType;
    private long fileSize;
    private String masterPlaylistUrl;
    private List<StreamQuality> qualities;
    private boolean processedForStreaming;
    private double buyPrice;
    private double rentalPrice;
    private List<Integer> rentalDurations; // in hours, e.g., 24, 48

    // AI Analysis Fields
    private double cinematicQualityScore; // 0-5 points
    private double storyQualityScore; // 0-5 points (equivalent to 0.5 in overall)
    private double overallRating; // Calculated based on scores
    private String pricingTier; // Based on overallRating: e.g., "BASIC", "STANDARD", "PREMIUM"

    // Metadata for analysis
    private Integer resolutionWidth;
    private Integer resolutionHeight;
    private Double frameRate;
    private String colorGrading; // e.g., "CINEMATIC", "STANDARD", "RAW"
    private String soundQuality; // e.g., "STEREO", "SURROUND", "MONO"
    private String synopsis;
    private Double viewerRating; // From reviews
    private Double watchThroughRate; // Percentage of viewers who finish

    public Movie() {
        this.qualities = new ArrayList<>();
        this.rentalDurations = new ArrayList<>();
    }

    public Movie(Long id, String title, String description, String s3Url, String fileName, String contentType, long fileSize) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.s3Url = s3Url;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.qualities = new ArrayList<>();
        this.processedForStreaming = false;
        this.rentalDurations = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getS3Url() {
        return s3Url;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMasterPlaylistUrl() {
        return masterPlaylistUrl;
    }

    public void setMasterPlaylistUrl(String masterPlaylistUrl) {
        this.masterPlaylistUrl = masterPlaylistUrl;
    }

    public List<StreamQuality> getQualities() {
        return qualities;
    }

    public void setQualities(List<StreamQuality> qualities) {
        this.qualities = qualities;
    }

    public boolean isProcessedForStreaming() {
        return processedForStreaming;
    }

    public void setProcessedForStreaming(boolean processedForStreaming) {
        this.processedForStreaming = processedForStreaming;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public List<Integer> getRentalDurations() {
        return rentalDurations;
    }

    public void setRentalDurations(List<Integer> rentalDurations) {
        this.rentalDurations = rentalDurations;
    }

    public void addQuality(StreamQuality quality) {
        this.qualities.add(quality);
    }

    // Getters and Setters for AI Analysis Fields
    public double getCinematicQualityScore() {
        return cinematicQualityScore;
    }

    public void setCinematicQualityScore(double cinematicQualityScore) {
        this.cinematicQualityScore = cinematicQualityScore;
    }

    public double getStoryQualityScore() {
        return storyQualityScore;
    }

    public void setStoryQualityScore(double storyQualityScore) {
        this.storyQualityScore = storyQualityScore;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public String getPricingTier() {
        return pricingTier;
    }

    public void setPricingTier(String pricingTier) {
        this.pricingTier = pricingTier;
    }

    public Integer getResolutionWidth() {
        return resolutionWidth;
    }

    public void setResolutionWidth(Integer resolutionWidth) {
        this.resolutionWidth = resolutionWidth;
    }

    public Integer getResolutionHeight() {
        return resolutionHeight;
    }

    public void setResolutionHeight(Integer resolutionHeight) {
        this.resolutionHeight = resolutionHeight;
    }

    public Double getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(Double frameRate) {
        this.frameRate = frameRate;
    }

    public String getColorGrading() {
        return colorGrading;
    }

    public void setColorGrading(String colorGrading) {
        this.colorGrading = colorGrading;
    }

    public String getSoundQuality() {
        return soundQuality;
    }

    public void setSoundQuality(String soundQuality) {
        this.soundQuality = soundQuality;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Double getViewerRating() {
        return viewerRating;
    }

    public void setViewerRating(Double viewerRating) {
        this.viewerRating = viewerRating;
    }

    public Double getWatchThroughRate() {
        return watchThroughRate;
    }

    public void setWatchThroughRate(Double watchThroughRate) {
        this.watchThroughRate = watchThroughRate;
    }
}