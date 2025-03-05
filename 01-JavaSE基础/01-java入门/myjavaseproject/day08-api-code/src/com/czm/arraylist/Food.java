package com.czm.arraylist;
//@Data
public class Food {
    private String name;
    private double price;
    private String desc;

    // 1、定义构造器
    public Food() {}

    // 2、定义 set/get 方法
    // 快捷方式：在类中右键 > generate/生成 > getter and setter > 勾选所有成员属性
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
