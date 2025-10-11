package com.czm.d5_dataObjStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 Serializable 接口，用于 对象流 进行 I/O 操作
 */
public class Person implements Serializable {
    private String name;
    private int age;
    private double height;

//    private static final long serialVersionUID = 1L;

    /**
     * transient 指定 car 成员变量不被序列化
     */
    private transient Car car;
    private List<Book> books = new ArrayList<>();


    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Person(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", car=" + car +
                ", books=" + books +
                '}';
    }
}
