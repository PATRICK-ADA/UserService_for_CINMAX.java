package com.example.userservice.model;

import java.util.List;
import java.util.ArrayList;

public class Movie {
    private Long id;
    private String title;
    private String description;
    private byte[] fileData;
    private String fileName;
    private String contentType;
    private String masterPlaylistUrl;
    private List<StreamQuality> qualities;
    private boolean processedForStreaming;

    public Movie() {
        this.qualities = new ArrayList<>();
    }

    public Movie(Long id, String title, String description, byte[] fileData, String fileName, String contentType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.fileData = fileData;
        this.fileName = fileName;
        this.contentType = contentType;
        this.qualities = new ArrayList<>();
        this.processedForStreaming = false;
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

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
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

    public void addQuality(StreamQuality quality) {
        this.qualities.add(quality);
    }
}