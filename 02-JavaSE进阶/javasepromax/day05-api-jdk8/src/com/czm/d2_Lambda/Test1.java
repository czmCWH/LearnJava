package com.czm.d2_Lambda;

public class Test1 {
    /*
     1、Lambda 表达式
        Lambda 表达式是 JDK 8 开始新增的一种语法形式;
        作用：用于简化匿名内部类的代码写法。
        语法格式：
	        (被重写匿名内部类的方法形参列表) -> {
		        // 被重写方法的方法体代码
	        }

        注意：Lambda 表达式只能简化 函数式接口 的匿名内部类!!!

     2、什么是函数式接口 ?
        有且仅有一个抽象方法的接口。
        注意：将来我们见到的大部分函数式接口，上面都可能会有一个 @Functionalinterface 的注解，有该注解的接口就必定是函数式接口。
     */
    public static void main(String[] args) {
        // 1、通过抽象类 创建 匿名内部类
        Animal a1 = new Animal() {
            @Override
            public void run() {
                System.out.println("--- 马儿飞驰奔跑！");
            }
        };

        // 2、如下报错：
        // Lambda 并不能简化所有匿名内部类的代码。只能简化函数式接口的匿名内部类。
//        Animal a2 = () -> {
//            System.out.println("--- 马儿飞驰奔跑！");
//        };
//        a2.run();;

        // 3、通过接口实现一个匿名内部类对象
        Swimming s1 = new Swimming() {
            @Override
            public void swim() {
                System.out.println("--- 学生游泳～");
            }
        };
        s1.swim();

        // 通过 Lambda 简化
        // 因为 上下文推断出真实的代码形式。通过 Swimming 推断出它只有一方法，方法结构一样，所以可以实现 Lambda 简化。
        Swimming s2 = () -> {
            System.out.println("--- 运动员游泳比赛～");
        };
        s2.swim();

    }
}
