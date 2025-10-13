package com.czm.d3_Interface;

public class Child {
    public String name;

    // 使用接口
    private TeachAbility teach;

    public Child(String name) {
        this.name = name;
    }

    public void setTeach(TeachAbility teach) {
        this.teach = teach;
    }

    public void study() {
        teach.teachCourse(this);
        teach.teachSport(this);
    }

    public void doSpecialty() {
        teach.teachMusic();
    }
}
