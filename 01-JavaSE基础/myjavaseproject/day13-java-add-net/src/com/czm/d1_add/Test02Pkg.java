package com.czm.d1_add;

public class Test02Pkg {
    /*
     1、Java 程序运行过程
      源代码（.java文件）--(编译)--> 字节码（.class文件）--(加载)--> JVM --(解析)--> 机器指令（CPU执行）

     2、Java 的工具包
      JDK 的 /bin 目录中已经包含了运行 Java 程序的必备工具：java.exe 和 javac.exe。
      Eclipse、IDEA等就是调用了这些工具将 Java 程序运行起来的。

      测试终端命令：
        Test.java 文件：
            public class Test {
                public static void main(String[] args) {
                    System.out.println("--- 测试 Java 程序运行！");
                }
            }

        javac Test.java，表示编译 Test.java 文件为 Test.class 文件；
        java Test，表示启动 JVM，加载 Test.class 文件；

     3、.class 文件
      每一个类（包括枚举）、接口 编译完毕后都会生成一个对应的 .class 文件，其特点如下：
            类型                  .class文件名
        顶级类型（类、接口）            类型名
        嵌套类型（类、接口）         外部类型 + $ + 嵌套类型
            局部类                 外部类型 + $ + 数字 + 局部类
            匿名类                 外部类型 + $ + 数字

        嵌套类型，是指类型的定义在其它类型内部；
        局部类，是指类型的定义在局部作用域，即方法内；

     4、main 方法参数
      main 方法参数 String[] args 是指当使用 java Main 11 22 方式运行时携带的 11 22 参数；

      main 方法的限制：
        必须是公共、静态（public static）；
        不能有返回值；
        有且只有1个参数，参数类型只能是 String[] 或者 String...；

     5、JAR
      编写好的 Java 程序提供给其他人使用的常见做法有如下几种做法：
        将相关的 .java 文件直接分享出去；
        将相关的 .java 文件打包成一个 .jar 文件后分享出去；

      jar 全称是 Java Archive，使用 ZIP 文件格式来打包。此方法本质上是调用了 JDK bin 目录中的 jar.exe 工具进行打包。
      可以对 JAR 进行数字签名，防止代码被篡改。

     6、Runnable JAR
      可运行 JAR（Runnable JAR），是指包含了程序入口 main 方法的 JAR 。

      启动 Runnable JAR 的方式：
       a、在终端执行：java -jar /JAR文件路径/xx.jar
       b、编写 bat 脚本文件，双击执行 bat 文件，bat文件的内容如下：（bat 脚本只适合 Windows）
          java -jar /JAR文件路径/xx.jar
          pause
       c、利用工具将 JAR 转为 exe 文件，双击执行 exe 文件（适用于 Windows，依然需要 JVM 的支持）。

     */

    public static void main(String[] args) {
        try {
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            System.out.printf("n1 = %d  n2 = %d", n1, n2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
