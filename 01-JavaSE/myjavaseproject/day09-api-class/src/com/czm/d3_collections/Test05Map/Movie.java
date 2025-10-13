package com.czm.d3_collections.Test05Map;

import java.util.Objects;

public class Movie {
    private String name;
    private double score;
    private String actor;

    public Movie() {}

    public Movie(String name, double score, String actor) {
        this.name = name;
        this.score = score;
        this.actor = actor;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getActor() {
        return actor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Movie movie = (Movie) object;
        return Double.compare(score, movie.score) == 0 && Objects.equals(name, movie.name) && Objects.equals(actor, movie.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, actor);
    }
}
