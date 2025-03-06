package com.czm.d8_extends_modifier;

public class Father {

    private void privateMethod() {
        System.out.println("--- private method");
    }

    void method() {
        System.out.println("--- 缺省权限修饰符");
        privateMethod();
    }

    protected void protectedMethod() {
        System.out.println("--- protected method");
    }

    public void publicMethod() {
        System.out.println("--- public method");
    }

}
