package com.czm.d11_extends_field_x;

public class Test {

    /*
     1、子类中访问其他成员的特点
        在子类方法中访问其他成员(成员变量、成员方法)，是依照就近原则的
        先子类局部范围找 -> 然后子类成员范围找 -> 然后父类成员范围找，如果父类范围还没有找到则报错。

     2、如果子父类中，出现了重名的成员，会优先使用子类的，如果此时一定要在子类中使用父类的怎么办?
        可以在子类中通过 super 关键字，指定访问父类的成员：super.父类成员变量/父类成员方法
     */
    public static void main(String[] args) {
        Zi zi = new Zi();
        zi.printName();
        zi.doSomething();
    }
}

class Fu {
    String name = "父类名字";

    void doSomething() {
        System.out.println("---父类中的 doSomething 方法");
    }
}

class Zi extends Fu {
    String name = "子类的名字";

    void printName() {
        System.out.println(this.name);
        System.out.println(super.name);
    }

    @Override
    void doSomething() {
        System.out.println("---子类中的 doSomething 方法");
        super.doSomething();    // 访问父类的方法
    }
}