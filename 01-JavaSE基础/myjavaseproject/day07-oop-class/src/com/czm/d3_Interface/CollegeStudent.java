package com.czm.d3_Interface;

public class CollegeStudent implements TeachAbility {

    @Override
    public void teachCourse(Child child) {
        System.out.println("--- 大学生教" + child.name + "学习现代经济学！");
    }

    @Override
    public void teachSport(Child child) {
        System.out.println("--- 大学生教" + child.name + "滑雪！");
    }

    // 实现接口的默认方法
    @Override
    public void teachMusic() {
        // ⚠️ TeachAbility.super 调用接口的默认方法
        TeachAbility.super.teachMusic();
        System.out.println("--- college student teach music ---");
    }
}
