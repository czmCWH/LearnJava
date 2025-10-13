package com.czm.branch;

public class Demo3Switch {
    public static void main(String[] args) {
        /*
         switch 的注意事项
         1、表达式类型只能是byte、short、int、char，JDK5开始支持枚举，JDK7开始支持String、不支持double、float、long。
         2、case给出的值不允许重复，且只能是字面量，不能是变量。
         3、正常使用switch的时候，不要忘记写break，否则会出现穿透现象。
            当存在多个case分支的代码相同时，可以把相同的代码放到一个case块中，其他的case块都通过穿透性穿透到该case块执行代码即可，这样可以简化代码。
         */
        short a = 23;
        String weekDay = "周三";
        long lg = 1231231L;
        double d = 123.123;

        // 计算机二进制计算小数本身就不精确
        System.out.println(0.1 + 0.2);

    }
}
