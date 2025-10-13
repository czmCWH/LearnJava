package com.czm.d2_static_method_x;

public class TestDemo1 {
    /*
      1、成员方法的分类
        a、类方法(静态方法)：有 static 修饰的成员方法，属于类；
	        访问方式：
			    类名.类方法名
			    对象.类方法 --- 不推荐

        b、实例方法(对象方法)：无 static 修饰的成员方法，属于对象。
	        访问方式：
			    对象.实例方法

      2、类方法的原理
         类被加载到方法区，成员方法+静态方法；
         通过类名在栈中访问静态方法；
         对象调用类方法：对象变量找到堆内存中的对象，然后通过其类地址找到静态方法执行；
     */
    public static void main(String[] args) {
        // 执行静态方法
        Student.printSay();

        Student s1 = new Student();
        s1.score = 100;
        // 执行对象方法
        s1.printScore();
        // 对象执行类方法
        s1.printSay();
    }
}
