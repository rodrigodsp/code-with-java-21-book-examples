package com.codewithjava21.weatherapp;

import java.time.Instant;

public class Properties {
    
    private String station;
    private Instant timestamp;
    private String icon;
    private Measurement temperature;
    private Measurement windDirection;
    private Measurement windSpeed;
    private Measurement windGust;
    private Measurement visibility;
    private Measurement precipitationLastHour;
    private CloudLayer[] cloudLayers;


    public String getStation() {
        return this.station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Measurement getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Measurement temperature) {
        this.temperature = temperature;
    }

    public Measurement getWindDirection() {
        return this.windDirection;
    }

    public void setWindDirection(Measurement windDirection) {
        this.windDirection = windDirection;
    }

    public Measurement getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(Measurement windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Measurement getWindGust() {
        return this.windGust;
    }

    public void setWindGust(Measurement windGust) {
        this.windGust = windGust;
    }

    public Measurement getVisibility() {
        return this.visibility;
    }

    public void setVisibility(Measurement visibility) {
        this.visibility = visibility;
    }

    public Measurement getPrecipitationLastHour() {
        return this.precipitationLastHour;
    }

    public void setPrecipitationLastHour(Measurement precipitationLastHour) {
        this.precipitationLastHour = precipitationLastHour;
    }

    public CloudLayer[] getCloudLayers() {
        return this.cloudLayers;
    }

    public void setCloudLayers(CloudLayer[] cloudLayers) {
        this.cloudLayers = cloudLayers;
    }

}
