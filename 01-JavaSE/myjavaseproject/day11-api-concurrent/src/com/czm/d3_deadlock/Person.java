package com.czm.d3_deadlock;

public class Person {
    private String name;
    public Person(String name) {
        this.name = name;
    }

    public synchronized void hello(Person p) {
        System.out.printf("[%s] hello to [%s]%n", name, p.name);
        p.smile(this);
    }

    public synchronized void smile(Person p) {
        System.out.printf("[%s] smile to [%s]%n", name, p.name);
    }
}
