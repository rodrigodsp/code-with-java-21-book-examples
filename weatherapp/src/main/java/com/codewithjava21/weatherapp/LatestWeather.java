package com.codewithjava21.weatherapp;

public class LatestWeather {
    
    private String id;
    private Geometry geometry;
    private Properties properties;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Geometry getGeometry() {
        return this.geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
