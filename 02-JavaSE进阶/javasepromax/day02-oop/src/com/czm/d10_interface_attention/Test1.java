package com.czm.d10_interface_attention;

public class Test1 {

    //  2、一个类实现多个接口，如果多个接口中存在方法签名名冲突，则此时不支持多实现。

    public static void main(String[] args) {

    }
}


interface A1 {
    void run();
}
interface B1 {
    //    String run();     // 报错
    void run();
}
// 如果 B1 接口定义了 String run(); 那么 C1 类实现 A1, B1 接口会报错。
class C1 implements A1, B1 {

    @Override
    public void run() {

    }
}