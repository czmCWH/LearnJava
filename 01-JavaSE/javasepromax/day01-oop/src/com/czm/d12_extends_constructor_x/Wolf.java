package com.czm.d12_extends_constructor_x;

public class Wolf extends Animal {
    public Wolf() {
        super();
        System.out.println("--- Wolf 本身的无参构造器 - 1");
    }

    public Wolf(String name) {
        super();
        System.out.println("--- Wolf 本身的有参构造器 - 2");
    }
}
