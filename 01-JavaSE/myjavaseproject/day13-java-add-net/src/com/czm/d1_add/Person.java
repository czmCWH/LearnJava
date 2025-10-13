package com.czm.d1_add;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("跑路");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        return name.equals(((Person) obj).name);
//        if (obj instanceof Person) {}
    }
}
