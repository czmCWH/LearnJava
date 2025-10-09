package com.czm.d2_AbstractClass;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected void calculate() {
        area = width * height;
        girth = (width + height) * 2;
    }
}
