package com.czm.d4_Encapsulation;

public class Test02ToString {
    /*
     1、toString 方法
      当打印一个对象时，会自动调用对象的 toString 方法，并将返回的字符串打印出来。

      toString 方法来源于基类 java.lang.Object，默认实现如下：
        public String toString() {
            return getClass().getName() + "@" + Integer.toHexString(hashCode());
        }

      一般会重写 toString 表示类的所有信息。

     2、

     */
    public static void main(String[] args) {
        Person p = new Person();
        p.name = "康康";
        p.setAge(18);

        System.out.println("--- p = " + p); // Person{name='康康', age=18, height=0.0, weight=0.0}

    }
}
