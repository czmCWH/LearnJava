package com.czm.d5_set_x;

import java.util.Objects;


public class Student {
    private String name;
    private String hobby;

    public Student(String name, String hobby) {
        this.name = name;
        this.hobby = hobby;
    }

    // 只要对象的内容一样，则返回 true
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(hobby, student.hobby);
    }

    // 重写 hashCode 方法，表示只要对象的属性值一样，则对象的 hashCode 相等。
    @Override
    public int hashCode() {
        return Objects.hash(name, hobby);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
