package com.czm.d5_static_final;

/*
 1、静态导入
  作用：使用了静态倒入后，就可以省略类名来访问静态成员（成员变量、方法、嵌套类）

 2、静态导入优缺点
  正确使用静态导入，可以消除一些重复的类名，提高代码的可读性；
  过度使用静态导入，会让读者分不清静态成员是在那个类中定义的；
  建议：谨慎使用！

 */

// 如下所示：导入 Person 中所有静态成员
import static com.czm.d5_static_final.Person.*;

// 静态导入的经典应用场景：导入 PI 变量
import static java.lang.Math.PI;

public class Test02ImportStatic {
    public static void main(String[] args) {
        // 静态导入
        System.out.println(count);
        test();

        System.out.println(Math.PI);
        System.out.println("静态导入 直接使用 PI，不需要携带 Math 前缀 = " + PI);
    }
}
