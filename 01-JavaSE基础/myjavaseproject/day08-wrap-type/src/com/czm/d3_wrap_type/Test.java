package com.czm.d3_wrap_type;

import java.util.Objects;

public class Test {

    /*
     1、基本类型的缺陷
     对比引用类型，基本类型存在的一些缺陷：
        a、无法表示不存在的值(nu11 值)。
        b、不能利用面向对象的方式去操作基本类型(比如直接用基本类型 调用方法)。
        c、当方法参数是引用类型时，基本类型无法传递。

     解决方案：可以自己将基本类型包装成引用类型。

     2、基本类型的包装类
     Java 中内置了基本类型的包装类(都在 java.lang 包中)。包装类的作用是把基本类型的数据包装成对象。
     其中，数字类型的包装类（Byte、Short、Integer、Long、Float、Double）最终都继承自 java.lang.Number。
        基本数据类型：
	        byte、char、short、int、long、float、double、boolean
        对应的包装类（引用数据类型）：
	        Byte、Character、Short、Integer、Long、Float、Double、Boolean

	 核心目的：由于泛型和集合都不支持基本数据类型，因为万物皆对象，因此包装类在集合和泛型中大量使用而且是必须使用。
     */

    public static void main(String[] args) {
        System.out.println("------------ 1、自定义引用类型 来解决 基本类型的缺陷");
        /*
         如下数组，如果元素使用基本类型，则无法使用 null 表示没有的状态，并且无法调用方法。
         */
        IntObject[] data = { new IntObject(100), null, new IntObject(300), new IntObject(0) };
        for(IntObject obj : data) {
            if (obj != null) {
                System.out.println("--- 当前值为：" + obj.value);
            } else {
                System.out.println("---- 当前没有值");
            }
        }

        /*
         3、自动装箱、拆箱(Autoboxing and Unboxing)
            a、自动装箱：Java 编译器会自动调用 .value0f 方法，将基本类型转换为包装类。
            b、自动拆箱：Java 编译器会自动调用 xxxValue 方法，将包装类转换为基本类型。
         */
        System.out.println("----- 自动装箱");
        Integer i1 = 199;
//        Integer i1 = Integer.valueOf(199);    // 等价于
        add(200);
//        add(Integer.valueOf(200));    // 等价于

        System.out.println("----- 自动拆箱");
        Character c1 = 'a';
        char c2 = c1;
//        char c2 = c1.charValue();     // 等价于
        System.out.println("--- c2 = " + c2);

        System.out.println(i1 == 199);
//        System.out.println(i1.intValue() == 199);     // 等价于

        Integer[] array = { 100, null, 300, 0 };
        int result = 0;
        for(Integer obj : array) {
            if (obj != null) {
                result += obj;
//                result += obj.intValue();     // 等价于
            }
        }
        System.out.println("----- result = " + result);

        /*
         4、⚠️ 包装类的判等，不要使用 ==、!= 运算符，应该使用 equals 或 Objects.equals 方法。
            ==、!= 比较的是变量的内存地址。
            引用类型的 equals 方法比较的是它们包装的值是否相等。

          Integer 的缓存对象：
            IntegerCache 类中缓存了 [-128, 127] 范围的 Integer 对象。
            Integer.valueOf 方法会优先去 IntegerCache 缓存中获取 Integer 对象，如果获取不到则新创建。
         */
        System.out.println("---- 包装类型的比较");
        Integer num1 = 127;     // 等价于 Integer num1 = Integer.valueOf(127)
        Integer num2 = 127;     // 等价于 Integer num2 = Integer.valueOf(127)
        Integer num3 = 128;     // 等价于 Integer num3 = Integer.valueOf(128)
        Integer num4 = 128;     // 等价于 Integer num4 = Integer.valueOf(128)

        System.out.println(num1 == num2);   // true，因为 num1 和 num2 变量存储的内存地址值相等。
        System.out.println(num3 == num4);   // false，因为 num3 和 num4 变量存储的内存地址值不相等。

        System.out.println(num1.equals(num2));  // true，因为 num1 和 num2 包装的值相等。
        System.out.println(num3.equals(num4));  // true，因为 num3 和 num4 包装的值相等。

        System.out.println("---- Objects.equals 方法比较对象是否相等 = " + Objects.equals(num3, num4));


        System.out.println("--- new Integer 会生成新对象");
        Integer n1 = 88;
        Integer n2 = Integer.valueOf(88);
        Integer n3 = new Integer(88);   // ⚠️：new Integer 新创建一个对象，不会从 Integer 缓存内获取。
        System.out.println(n1 == n2);   // true，因为 n1 和 n2 的引用地址相等。
        System.out.println(n2 == n3);   // false，因为 new Integer 会创建新对象，所以他们的引用地址不同。


        /*
         5、基本类型 的 包装类 使用注意点：
            【基本类型数组】与【包装类数组】之间是不能自动装箱、拆箱的。
         */
        int[] nums1 = { 1, 2, 3 };
//        Integer[] nums2 = nums1;    // 编译会报错！！！
        Integer[] nums2 = new Integer[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            nums2[i] = nums1[i];
        }
        System.out.println(nums2);

    }

    static void add(Integer value) {}
}
