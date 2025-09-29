package com.czm.d1_add;

public class Test01GUI {

    /*

     1、GUI（Graphical User Interface）
      GUI：图形用户界面，指在计算机中采用图形方式显示的用户界面。

      1970年代，美国施乐公司的研究人员开发出了第一个图形用户界面，这一设计使得计算机实现了字符界面向图形界面的转变。
      后来，微软、苹果等公司的操作系统陆续出现，界面设计不断完善。现在日常生活中使用的操作系统、应用程序都是基于 GUI 的。

     2、Java  的 GUI 编程方案
      Java 也可以开发 GUI 程序，常见的实现方案有4种：
        a、AWT（Abstract Window Toolkit）
        Java 官方最早推出的 GUI 编程开发包，界面风格是跟随操作系统的。

        b、SWT（Standard Widget Toolkit）
        由IBM推出，著名的开发工具 Eclipse 就是 SWT 开发的。

        c、Swing
        Swing 是在 AWT 的基础上扩充了功能，灵活且强大，在不同操作系统中可以保持统一风格。

        d、JavaFX
        Java 官方推出的最新一代 GUI 编程开发包，参考资料比 Swing 少。

     */
    public static void main(String[] args) {
        // 案例：使用 Swing 实现加法计算器
        new MyFrame();
    }
}
