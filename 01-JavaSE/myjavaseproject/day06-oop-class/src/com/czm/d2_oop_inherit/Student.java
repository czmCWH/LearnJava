package com.czm.d2_oop_inherit;

public class Student extends Person {
    public int no;
    public void study() {
        System.out.println(age + "_" + no + "_study");
    }

    // 子类中可以定义跟父类同名的成员变量
    public int sameOne = 22;
    public void printSameOne() {
        System.out.println("sameOne = " + sameOne);
        System.out.println("this.sameOne = " + this.sameOne);
        System.out.println("super.sameOne = " + super.sameOne);
    }

    /**
     * 重写父类的 eat 方法
     */
    @Override
    public void eat() {
        // 通过 super 调用 父类的方法
        super.eat();

        System.out.println("Student ---> eat");
    }

    // 如下方法与父类的方法签名不同，所以不存在重写
    // 方法签名 = 方法名 + 参数类型
    public void eat(String food) {
        System.out.println("Student eat " + food);
    }

    // 构造方法
    public Student() {
        super();  // super 调用父类的构造方法，super(); 默认写不写都有
//        this(12);   // this 调用本类的构造方法
        System.out.println("Student constructor");
    }
    public Student(int age) {
//        super(age);
    }

}
