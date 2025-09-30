package com.czm.d6_init;

public class Person {
    static {
        System.out.println("--- 1 - Person - static block");
    }

    {
        System.out.println("--- 1 - Person - block");
    }

    public Person() {
        System.out.println("--- Person constructor");
    }
}
