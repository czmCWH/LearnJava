package com.czm.d7_enum_x;

public enum A {
    // 1、枚举类的第一行必须罗列的是枚举对象的名称。
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
