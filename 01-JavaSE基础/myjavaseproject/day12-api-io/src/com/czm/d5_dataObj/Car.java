package com.czm.d5_dataObj;

import java.io.Serializable;

public class Car implements Serializable {
    private double price;
    private String brand;

    public Car(double price, String brand) {
        this.price = price;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}
