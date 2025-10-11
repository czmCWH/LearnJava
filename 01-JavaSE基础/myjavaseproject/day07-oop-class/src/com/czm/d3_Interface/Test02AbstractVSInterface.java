package com.czm.d3_Interface;

public class Test02AbstractVSInterface {

    /*
     抽象类 vs 接口：
        继承：A extends B，表示 A 是 B ；
        接口：A implements B, C，表示 A 拥有 B、C 的行为；

     1、何时选择抽象类？
       在紧密相关的类之间共享代码，即存在继承关系；
       需要除 public 之外的访问权限，如一些 private 成员：
       需要定义实例变量、非 final 的静态变量，这些能力接口无法实现；

     2、何时选择接口？
       不相关的类实现相同的方法；
       只是定义特定数据类型的行为，不关心具体是谁实现了行为；
       想实现类型的多重继承，接口之间可以多继承，并且类型可以实现多个接口；

     */

    public static void main(String[] args) {

    }
}
