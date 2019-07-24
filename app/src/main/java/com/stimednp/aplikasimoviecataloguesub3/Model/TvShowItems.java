package com.stimednp.aplikasimoviecataloguesub3.Model;

/**
 * Created by rivaldy on 7/23/2019.
 */

public class TvShowItems {
    private String name;
    private String original_name;
    private String first_air_date;
    private Double vote_average;
    private String vote_count;
    private String overview;
    private String poster_path;
    private String backdrop_path;

    public TvShowItems(String name, String original_name, String first_air_date, Double vote_average, String vote_count, String overview, String poster_path, String backdrop_path) {
        this.name = name;
        this.original_name = original_name;
        this.first_air_date = first_air_date;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
