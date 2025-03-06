package com.czm.d14_object;

public class Test {
    /*
     1、API
        API(Application Programming interface)：应用程序编程接口。
        就是 Java 帮我们已经写好一些程序（或者其它第三方），如：类、方法等，我们直接拿过来用就可以解决一些问题。

     2、Object类
        0bject 类是 Java 中所有类的祖宗类，因此，Java 中所有类的对象都可以直接使用 Object 类中提供的一些方法。

     3、Object 类常见方法
        toString()	返回对象的字符串表示形式。
	    equals()	判断两个对象是否相等（即：对象的地址是否相等）。

     */
    public static void main(String[] args) {
        Student s1 = new Student("小明", 18, 650);
        Student s2 = new Student("小明", 18, 650);

        System.out.println("---------------1、toString 方法--------------------");

        // 1、直接输出对象时，toString 方法可以省略
        System.out.println(s1.toString());
        // ⚠️：默认返回对象的所谓的地址形式其实是没有啥意义的，开发中输出对象更想看内容，因此 toString 是用来提供给子类重写。
        System.out.println(s1);

        System.out.println("---------------2、equals 方法--------------------");
        // ⚠️：比较两个对象的地址是否一样可以直接用 “==” 比较，完全没有必要用 equals 去比较。
        // 因此 0bject 提供的 equals 的意义是为了让子类重写，以便自己制定比较规则(按照内容比较!)
        System.out.println(s1.equals(s2));  // 打印：true
        System.out.println(s1 == s2);       // 打印：false
    }
}
