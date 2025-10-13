package com.czm.d3_collections.Test05Map;

public class Book implements Comparable<Book> {
    private String name;
    private double score;
    private String actor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Book() {}

    public Book(String name, double score, String actor) {
        this.name = name;
        this.score = score;
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", actor='" + actor + '\'' +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        return Double.compare(this.score, o.score);
    }
}
