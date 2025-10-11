package com.czm.d3_collections.Test06Collections;

/**
 * 定义一个 扑克牌 对象
 */
public class Card {
    // 点数
    private String number;
    // 花色
    private String color;
    // 记录 牌的大小
    private int size;

    @Override
    public String toString() {
        return number + color;
    }

    public Card() {

    }

    public Card(String number, String color, int size) {
        this.size = size;
        this.color = color;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
