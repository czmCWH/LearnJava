package com.czm.d3_method_reference;

public class Test4 {
    /*
     1、构造器引用
        语法：类名::new

     2、使用场景
         如果某个 Lambda表达式 里只是在创建对象，并且前后参数情况一致，就可以使用构造器引用。

     */
    public static void main(String[] args) {

        // 1、通过接口创建匿名内部类对象
//        CreateCar c1 = new CreateCar() {
//            @Override
//            public Car create(String name) {
//                return new Car(name);
//            }
//        };

        // 2、使用 Lambda表达式 简化
//        CreateCar c1 = name -> new Car(name);

        // 3、使用 构造器引用 简化 Lambda表达式
        CreateCar c1 = Car::new;

        Car car = c1.create("宝马");
        System.out.println(car.toString());

        System.out.println("--- 使用 构造器引用 简化 Lamdba 表达式");


    }
}
