package com.czm.d12_genericity_method_x;

public class Test {
    /*
     1、泛型方法定义：
	    修饰符 <泛型变量1, 泛型变量2...> 返回值类型 方法名(形参列表) {
	    }
     */
    public static void main(String[] args) {
        String[] names = {"彭于晏", "武大郎"};
        String str = printArray(names);
        System.out.println(str);
        // ⚠️：Java泛型不支持基本数据类型（如 double、int），只能使用其包装类（如 Double、Integer）。
        // 为了面向对象，泛型编译完后底层都是 Object 来接收，所以不支持基本数据类型。
        // double[] numbers = {1.1, 2.2, 3.3};  // 改为包装类数组
        Double[] numbers = {1.1, 2.2, 3.3};  // 改为包装类数组
        Double num = printArray(numbers);    // 正常执行
        System.out.println(num);
    }

    public static <T> T printArray(T[] list) {
        return list[0];
    }
}
