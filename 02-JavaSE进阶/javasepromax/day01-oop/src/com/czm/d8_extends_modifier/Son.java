package com.czm.d8_extends_modifier;

public class Son extends Father {
    public void sonPrint() {
//        privateMethod();  // 报错
        method();
        protectedMethod();
        publicMethod();
    }
}
