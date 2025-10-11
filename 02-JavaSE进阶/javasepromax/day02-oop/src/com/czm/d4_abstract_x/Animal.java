package com.czm.d4_abstract_x;

// 定义 Animal 抽象类
public abstract class Animal {
    private String name;

    /*
     ⚠️：抽象类的优点：
        方法无意义时，可以不写方法的实现，相比较继承简化了代码；
        强制子类重写抽象方法，更好的支持多态；
     */
    // 定义抽象方法
    public abstract void cry();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
