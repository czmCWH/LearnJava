package com.czm.d10_extends_override_x;

public class Tiger extends Animal{
    // 重写父类的方法
    @Override
    public void run() {
//        super.run();
        System.out.println("--- 老虎跑的好快");
    }

    @Override
    public String toString() {
        return "我是个老虎";
    }
}
