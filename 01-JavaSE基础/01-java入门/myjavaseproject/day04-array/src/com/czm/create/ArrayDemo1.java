package com.czm.create;

public class ArrayDemo1 {
    public static void main(String[] args) {
        /*
         1、数组
            数组就是一个容器，用来存一批同种类型的数据。
            遇到批量数据的存储和操作时，数组比变量更适合
            数组是引用类型，除了8种基础类型，其它都是引用类型；

         3、静态初始化数组，定义数组的时候直接给数组赋值。
         语法格式：
	            数据类型[] 数组名 = new 数据类型[]{元素1, 元素2, 元素3...}
         注意：'数据类型[] 数组名”  也可写成  “数据类型 数组名[]”
         */

        // 数组完整格式：
        int[] ages = new int[]{18, 19, 18, 22, 19};
        double[] scores = new double[]{80, 55.3, 59.5, 90, 100.0};

        // 简写格式
        double[] heights = {175.5, 165.3, 180};
        String names[] = {"张三", "李四", "王五"};
        System.out.println(names.length);

        /*
         4、数组的访问
            数组名[索引]
            通过数组名访问数组对象，根据索引访问某个位置的元素。
         */
        System.out.println("数组元素的个数 = " + heights.length);
        System.out.println("访问第0个元素 = " + heights[0]);
        System.out.println("访问数组最后一个元素 = " + heights[heights.length - 1]);

        /*
         5、数组的遍历
         遍历：对一个一个数据的访问。
         */
        int[] arr = {33, 66,33, 67, 998};
        for (int i = 0; i < arr.length; i++) {
            System.out.println("遍历数组 =" + arr[i]);
        }

        int[] money = {16, 26, 88, 10, 23, 190};
        int sum = 0;
        for (int i = 0; i < money.length; i++) {
            sum += money[i];
        }
        System.out.println("---数组元素累加 = " + sum);

    }
}
