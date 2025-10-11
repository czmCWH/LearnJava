package com.czm.d1_NestedClass;

/**
 * 2、定义内部类访问其外部类的同名属性
 */
public class OuterClass {
    private int x = 1;

    public class InnerClass {
        private int x = 2;
        public void print() {
            System.out.println("\n---内部类中访问同名属性：");
            System.out.println(x);  // 2
            System.out.println(this.x); // 2
            System.out.println(OuterClass.this.x);  // 1
        }
    }
}
