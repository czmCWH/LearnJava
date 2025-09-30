package com.czm.object05javabean;

public class Test {
    /*
     1、什么是实体类
	    实体类是一种特殊形式的类。
	    实体类中的成员变量都要私有，并且要对外提供相应的getXxx，setXxx方法。
	    实体类中必须要有一个公共的无参的构造器。

	 2、实体类的作用
	    a、实体类的对象本身只用于存储对象数据；
        b、实体类的对象只负责数据存取，而对数据的处理交给其他类的对象来完成，以实现数据和数据业务处理相分离。
        这是软件设计中很流行的一种设计思想:分层思想。

     */
    public static void main(String[] args) {
        // 1、实体类对象
        Student s = new Student("张三", 18, 173.5);
        System.out.println(s.getName());
        // 2、业务操作对象
        StudentOperator so = new StudentOperator(s);
        so.printInfo();
    }
}
