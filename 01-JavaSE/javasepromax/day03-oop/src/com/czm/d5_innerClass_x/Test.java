package com.czm.d5_innerClass_x;

public class Test {
    /*
     1、匿名内部类的使用场景
        通常作为一个对象参数传输给方法。
     2、
     */
    public static void main(String[] args) {

        System.out.println("=====通过实现类对象，多态实现=====");

        Animal animal = new Animal();
        playGame(animal);

        System.out.println("=====通过匿名内部类，它是接口的实现类对象=====");
        Swimming student = new Swimming() {
            @Override
            public void swim() {
                System.out.println("=== 学生开始游泳");
            }
        };
        playGame(student);
        System.out.println();
        // 注意：开发中通常这样写
        playGame(new Swimming() {
            @Override
            public void swim() {
                System.out.println("----随便一个人游泳");
            }
        });
    }

    public static void playGame(Swimming swimming) {
        System.out.println("--- 开始比赛");
        swimming.swim();
        System.out.println("--- 结束比赛");
    }
}
