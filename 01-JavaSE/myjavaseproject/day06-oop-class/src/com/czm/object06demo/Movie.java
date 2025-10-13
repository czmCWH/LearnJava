package com.czm.object06demo;
// 1、定义实体类，只负责数据的存取
public class Movie {
    // 1、定义成员变量
    private int id;
    private String name;
    private double price;
    private String actor;

    // 2、定义构造器
    public Movie() {}

    public Movie(int id, String name, double price, String actor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.actor = actor;
    }

    // 3、提供 get、set 方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
