package com.czm.d1_string;

import java.util.StringJoiner;

public class Test08StringJoiner {
    /*
     1、为什么学 StringJoiner ？
        适用于 有间隔符号 的字符串拼接场景，并不是为了取代 StringBuilder。

     2、StringJoiner
        ⚠️JDK8 开始才有的，跟 StringBuilder一样，也是用来操作字符串的，也可以看成是一个容器，创建之后里面的内容是可变的。

     3、StringJoiner 好处
        不仅能提高字符串的操作效率，并且在有些场景下使用它操作字符串，⚠️代码会更简洁

     */
    public static void main(String[] args) {

        StringJoiner js1 = new StringJoiner(",");
        System.out.println("---- js1 = " + js1);
        js1.add("a");
        System.out.println("---- js1 = " + js1);

        StringJoiner js2 = new StringJoiner(",", "{", "}");
        js2.add("a");
        js2.add("b");
        System.out.println("---- js2 = " + js2);
        System.out.println(js2.length());


        int[] arr = {1,2,3,4,5,6,7,8,9};
        System.out.println("--- " + getArrayData(arr));
    }

    public static String getArrayData(int[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        // 1、创建 StringJoiner 对象
        StringJoiner sj = new StringJoiner(",", "[", "]");
        // 2、遍历数组拼接内容
        for (int i = 0; i < array.length; i++) {
            sj.add(Integer.toString(array[i]));
        }
        return sj.toString();
    }
}
