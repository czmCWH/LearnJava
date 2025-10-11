package com.czm.d4_runtime_x;

public class Test {
    /*
     1、Runtime
        代表程序所在的运行环境。
        Runtime 是一个单例。

     */
    public static void main(String[] args) {
        // 1、返回与当前 java 应用程序关联的运行时对象。
        Runtime jre = Runtime.getRuntime();

        // 2、终止当前运行的虚拟机，该参数用作状态代码；按照惯例，非0状态代码表示异常终止。
//        jre.exit(0);
//        System.out.println("结束");

        // 3、返回java虚拟机可用的处理器数量
        System.out.println("处理器数量：" + jre.availableProcessors());

        // 4、返回Java虚拟机中的内存总量，字节数
        System.out.println("Java 虚拟机中的内存总量 = " + jre.totalMemory()/1024.0/1024.0 + "MB");

        // 5、返回 Java 虚拟机中的可用内存
        System.out.println("Java 虚拟机中的可用内存 = " + jre.freeMemory()/1024.0/1024.0 + "MB");
        // 6、执行命令，启动程序，并返回代表该程序的对象
//        Process process = jre.exec("calc");
    }
}
