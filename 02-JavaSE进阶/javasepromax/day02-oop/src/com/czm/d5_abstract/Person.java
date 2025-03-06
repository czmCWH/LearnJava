package com.czm.d5_abstract;

// 定义抽象类
public abstract class Person {
    // 模版方法，用 final 修饰避免被子类重写
    public final void write() {
        System.out.println("\t\t\t《我爱中国》");
        System.out.println("\t中国有56个民族");
        // 调用抽象方法
        writeMain();

        System.out.println("\t我爱你中国，生活在中国好幸福！");
    }

    // 定义抽象方法
    public abstract void writeMain();

}
