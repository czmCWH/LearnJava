package com.czm.d8_extends_modifier;

public class Test1 {
    /*
     1、什么是权限修饰符?
	    用来限制类中的成员(成员变量、成员方法、构造器、代码块.)能够被访问的范围。

	 2、权限修饰符的种类
        private：只能在本类中被访问
        缺省：本类、同一个包中的类
        protected：本类、同一个包中的类、子孙类中被访问
        public：任意位置

	 3、什么是权限修饰符?
	    用来限制类中的成员(成员变量、成员方法、构造器、代码块.)能够被访问的范围。
     */
    public static void main(String[] args) {
        Father father = new Father();
//        father.privateMethod(); // 报错
        father.method();
        father.protectedMethod();
        father.publicMethod();

    }
}
