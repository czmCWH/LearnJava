package com.czm.d1_stringbuilder;

public class Test2 {
    /*
     1、StringBuilder 案例：
        设计一个方法，用于返回任意整型数组的内容，要求返回的数组内容格式如: [11，22，33]。

     */
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        System.out.println(getArrayData(arr));
    }

    public static String getArrayData(int[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        // 1、创建 StringBuilder 对象
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // 2、遍历数组拼接内容
        for (int i = 0; i < array.length; i++) {
            sb.append(i == array.length - 1 ? array[i] : array[i] + ", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
