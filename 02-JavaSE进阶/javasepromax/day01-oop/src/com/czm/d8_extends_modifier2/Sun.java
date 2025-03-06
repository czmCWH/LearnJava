package com.czm.d8_extends_modifier2;

import com.czm.d8_extends_modifier.Father;

public class Sun extends Father {
    public void printMethod() {
//        privateMethod();  // 报错
//        method(); // 报错
        protectedMethod();   // 子孙类中可访问
        publicMethod();
    }

    public static void main(String[] args) {
        Sun s = new Sun();
        s.protectedMethod();    // 子孙类中可访问
        s.publicMethod();
    }
}
