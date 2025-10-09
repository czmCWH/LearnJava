package com.czm.d2_AbstractClass;

/**
 * 定义抽象类，抽取公共成员，强制子类必须实现某些方法
 * Shape 实例化没有意义，所以定义为抽象类
 */
public abstract class Shape {
    protected double area;
    protected double girth;

    public double getArea() {
        return area;
    }
    public double getGirth() {
        return girth;
    }

    /**
     * 定义抽象方法实现面积计算
     * 强制子类必须实现
     */
    protected abstract void calculate();

    public void showArea() {
        calculate();

        System.out.println("--- next do ---");
        System.out.println("area = " + area);
        System.out.println("girth = " + girth);
    }
}


