package com.czm.d8_extends_modifier2;

import com.czm.d8_extends_modifier_x.Father;

public class Demo {

    public static void main(String[] args) {
        Father father = new Father();
//        father.privateMethod();
//        father.method();
//        father.protectedMethod();
        father.publicMethod();

        Sun s = new Sun();
//        s.protectedMethod();  // 不在子孙类中，无法访问
        s.publicMethod();
    }
}
