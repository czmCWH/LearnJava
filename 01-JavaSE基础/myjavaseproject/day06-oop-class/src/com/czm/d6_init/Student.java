package com.czm.d6_init;

public class Student extends Person {
    static {
        System.out.println("--- 2 - Student - static block");
    }

    {
        System.out.println("--- 2 - Student - block");
    }

    public Student() {
        System.out.println("--- Student constructor");
    }

}
