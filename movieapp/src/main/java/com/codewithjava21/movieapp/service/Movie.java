package com.codewithjava21.movieapp.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.data.CqlVector;

@Table("movies")
public class Movie {
 
    @PrimaryKey("movie_id")
    private Integer moviedId;

    private String title;
    private String description;
    private float runtime;
    private String image;
    private Map<Integer,String> genres;
    private String website;
    private Long budget;
    private Long revenue;
    private Integer year;
    @Column("imdb_id")
    private String imdbId;
    @Column("original_language")
    private String originalLanguage;
    @Column("release_date")
    private LocalDate releaseDate;
    @Column("movie_vector")
    private CqlVector<Float> vector;


    public Integer getMoviedId() {
        return this.moviedId;
    }

    public void setMoviedId(Integer moviedId) {
        this.moviedId = moviedId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRuntime() {
        return this.runtime;
    }

    public void setRuntime(float runtime) {
        this.runtime = runtime;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<Integer,String> getGenres() {
        return this.genres;
    }

    public void setGenres(Map<Integer,String> genres) {
        this.genres = genres;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getBudget() {
        return this.budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Long getRevenue() {
        return this.revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImdbId() {
        return this.imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public CqlVector<Float> getVector() {
        return this.vector;
    }

    public void setVector(CqlVector<Float> vector) {
        this.vector = vector;
    }

}
