package com.codewithjava21.weatherapp;

import java.time.Instant;
import java.util.Map;

public class WeatherReading {
    
    private String stationId;
    private int montBucket;
    private Instant timestamp;
    private String readingIcon;
    private float stationCoordinatesLatitude;
    private float stationCoordinatesLongitude;
    private float temperatureCelsius;
    private int windDirectionDegrees;
    private float windSpeedKMH;
    private float windGustKMH;
    private int visibilityM;
    private float precipitationLastHour;
    private Map<Integer,String> cloudCover;


    public String getStationId() {
        return this.stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public int getMontBucket() {
        return this.montBucket;
    }

    public void setMontBucket(int montBucket) {
        this.montBucket = montBucket;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getReadingIcon() {
        return this.readingIcon;
    }

    public void setReadingIcon(String readingIcon) {
        this.readingIcon = readingIcon;
    }

    public float getStationCoordinatesLatitude() {
        return this.stationCoordinatesLatitude;
    }

    public void setStationCoordinatesLatitude(float stationCoordinatesLatitude) {
        this.stationCoordinatesLatitude = stationCoordinatesLatitude;
    }

    public float getStationCoordinatesLongitude() {
        return this.stationCoordinatesLongitude;
    }

    public void setStationCoordinatesLongitude(float stationCoordinatesLongitude) {
        this.stationCoordinatesLongitude = stationCoordinatesLongitude;
    }

    public float getTemperatureCelsius() {
        return this.temperatureCelsius;
    }

    public void setTemperatureCelsius(float temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public int getWindDirectionDegrees() {
        return this.windDirectionDegrees;
    }

    public void setWindDirectionDegrees(int windDirectionDegrees) {
        this.windDirectionDegrees = windDirectionDegrees;
    }

    public float getWindSpeedKMH() {
        return this.windSpeedKMH;
    }

    public void setWindSpeedKMH(float windSpeedKMH) {
        this.windSpeedKMH = windSpeedKMH;
    }

    public float getWindGustKMH() {
        return this.windGustKMH;
    }

    public void setWindGustKMH(float windGustKMH) {
        this.windGustKMH = windGustKMH;
    }

    public int getVisibilityM() {
        return this.visibilityM;
    }

    public void setVisibilityM(int visibilityM) {
        this.visibilityM = visibilityM;
    }

    public float getPrecipitationLastHour() {
        return this.precipitationLastHour;
    }

    public void setPrecipitationLastHour(float precipitationLastHour) {
        this.precipitationLastHour = precipitationLastHour;
    }

    public Map<Integer,String> getCloudCover() {
        return this.cloudCover;
    }

    public void setCloudCover(Map<Integer,String> cloudCover) {
        this.cloudCover = cloudCover;
    }
    
}
