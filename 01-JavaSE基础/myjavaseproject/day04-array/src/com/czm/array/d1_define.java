package com.czm.array;

import java.util.Arrays;

public class d1_define {
    public static void main(String[] args) {
        /*
         1、数组
            数组就是一个容器，用来存一批同种类型的数据。
            遇到批量数据的存储和操作时，数组比变量更适合

          如下所示，定义空数组：
         */
        int[] arr1;     // 推荐 int[] 格式定义数据
        int[] arr2 = {};
        int arr3[] = {};    // 不推荐 int arr3[] 格式定义数组
        System.out.println("--- 定义空数组 = " + arr3);  // 打印：[I@7291c18f

        // 定义数组时指定长度
        int[] arr4 = new int[5];
        System.out.println("--- 定义数组时指定长度 = " + arr4.length);   // 打印：5
        System.out.println("--- 把数组转换为 String = " + Arrays.toString(arr4)); // [0, 0, 0, 0, 0]

        // 定义多维数组


        /*
          2、静态初始化数组，定义数组的时候指定数组的元素
         语法格式：
	            数据类型[] 数组名 = new 数据类型[] {元素1, 元素2, 元素3...}
         注意：“数据类型[] 数组名”  也可写成  “数据类型 数组名[]”
         */
        int[] ages = new int[] {18, 19, 18, 22, 19};
        double[] scores = new double[] {80, 55.3, 59.5, 90, 100.0};
        char[] chars = new char[] {'A', 'B', 'C', 'D', 'E', 'F'};

        // 简写格式
        double[] heights = { 175.5, 165.3, 180 };
        System.out.println("---- heights.length = " + heights.length);


        /*
        3、动态初始化数组（常用）
        定义数组时先不存入具体的元素值，只确定数组存储的数据类型和数组的长度
        语法格式：
           数据类型[] 数组名 = new 数据类型[长度];

        数组是引用类型，数组元素存储在堆空间(heap)，Java 中只要使用 new 就是申请堆内存，并且会自动进行初始化。
        动态初始化数组时，会自动对数组元素完成默认初始化。数据类型默认值见图：[/day01-variable/img/03-java变量默认值.jpg]，默认值如下所示：
            基本类型：
                byte、short、char、int、long ： 0
                float、double：0.0
                boolean：false
            引用类型：
                类、接口、数组、String：null
        */

        String[] array1 = new String[10];
        double[] array2 = new double[10];
        int[] array3 = new int[10];
        boolean[] array4 = new boolean[10];
        System.out.println("----- 数组元素默认值");
        System.out.println("String[] = " + array1[0]);
        System.out.println("double[] = " + array2[0]);
        System.out.println("int[] = " + array3[0]);
        System.out.println("boolean[] + " + array4[0]);


        // list 变量在方法中是一个局部变量，它存放在main方法的栈空间。list 指向存放数组元素的堆空间。
        String[] list = new String[5];
        System.out.println("---- list[4] = " + list[4]);    // 打印：null。因为堆空间会自动初始化，所以 list 的元素默认值为 null。
        System.out.println("----打印数组变量 list = " + list);  // ⚠️ 打印：[Ljava.lang.String;@34a245ab <==> 数据类型+对象的hash值
        System.out.println("---- list = " + list.hashCode());   // 883049899
        System.out.println("---- list = " + 0x34a245ab);    // 883049899


        /*
         4、静态初始化数组 与 动态初始化数组 的区别：
            动态初始化：适合开始不确定具体元素值，只知道元素个数的业务场景；（常用）
            静态初始化: 适合一开始就知道要存入哪些元素值的业务场景；
         */


        /*
         5、数组的访问
            数组名[索引]
            通过数组名访问数组对象，根据索引访问某个位置的元素。
         */
        System.out.println("数组元素的个数 = " + heights.length);
        System.out.println("访问第0个元素 = " + heights[0]);
        System.out.println("访问数组最后一个元素 = " + heights[heights.length - 1]);


        /*
         6、数组的遍历
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

        // 遍历数组元素
        int i = 0;
        for(int ele : money) {
            System.out.println("index = " + i + ", value = " + ele);
            i++;
        }
    }
}
