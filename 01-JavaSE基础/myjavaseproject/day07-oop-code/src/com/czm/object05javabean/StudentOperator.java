package com.czm.object05javabean;
// 定义 StudentOperator 对象作为 Student 实体类的业务操作对象
public class StudentOperator {
    // 1、接收需要操作的实体类对象
    private Student s;
    public StudentOperator(Student s) {
        this.s = s;
    }
    // 2、打印总信息
    public void printInfo() {
        String info = "姓名：" + s.getName() + "，年龄：" + s.getAge() + "，身高：" +  s.getHeight();
        System.out.println(info);
    }

    // 3、其它业务操作
}
