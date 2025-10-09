package com.czm.d2_AbstractClass;

public class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    protected void calculate() {
        double half = Math.PI * radius;
        area = half * radius;
        girth = half * 2;
    }
}
