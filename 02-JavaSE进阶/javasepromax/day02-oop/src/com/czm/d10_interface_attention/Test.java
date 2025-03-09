package com.czm.d10_interface_attention;

public class Test {
    /*
     1、接口其他注意事项(了解)
     */
    public static void main(String[] args) {

    }
}
// 1、一个接口继承多个接口，如果多个接口中 存在方法签名冲突，则此时不支持多继承。
//      方法签名：即指方法名称、参数、返回值。
interface A {
    void run();
}
interface B {
//    String run();
    void run();
}
// 如果 B 接口定义了 String run(); 那么 C 接口继承 A, B 接口会报错。
interface C extends A, B {

}