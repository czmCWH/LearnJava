package com.czm.d5_abstract;

public class Test {
    /*
     1、抽象类的常见应用场景：
        模板方法设计模式，此模式 解决方法中存在重复代码的问题。

     2、模板方法设计模式的写法
	    1、定义一个抽象类。
	    2、在里面定义2个方法
		    一个是模板方法：把相同代码放里面去。⚠️模版方法用 final 修饰，避免被子类重写
		    一个是抽象方法：具体实现交给子类完成。

     3、abstract 与 final 是互斥的
     */
    public static void main(String[] args) {
        Student st = new Student();
        st.write();
        System.out.println("========");
        Teacher th = new Teacher();
        th.write();
    }
}
