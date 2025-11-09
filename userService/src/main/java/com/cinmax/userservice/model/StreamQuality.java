package com.cinmax.userservice.model;

public class StreamQuality {
    private String resolution;
    private long bandwidth;
    private String playlistUrl;
    private int width;
    private int height;

    public StreamQuality() {}

    public StreamQuality(String resolution, long bandwidth, String playlistUrl, int width, int height) {
        this.resolution = resolution;
        this.bandwidth = bandwidth;
        this.playlistUrl = playlistUrl;
        this.width = width;
        this.height = height;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public long getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(long bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getPlaylistUrl() {
        return playlistUrl;
    }

    public void setPlaylistUrl(String playlistUrl) {
        this.playlistUrl = playlistUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}