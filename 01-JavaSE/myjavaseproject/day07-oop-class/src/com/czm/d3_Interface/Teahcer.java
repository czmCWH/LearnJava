package com.czm.d3_Interface;

public class Teahcer implements TeachAbility {

    @Override
    public void teachCourse(Child child) {
        System.out.println("老师教" + child.name + "学习文化课程！");
    }

    @Override
    public void teachSport(Child child) {
        System.out.println("老师教" + child.name + "乒乓球！");
    }
}
