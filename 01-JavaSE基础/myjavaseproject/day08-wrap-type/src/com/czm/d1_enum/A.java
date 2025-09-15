package com.czm.d1_enum;

public enum A {
    // 1、枚举类的第一行必须罗列的是枚举常量，命名必须全大写，如果有多个单词用下划线隔开
    X, Y, Z;

    // 2、枚举其它成员
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
