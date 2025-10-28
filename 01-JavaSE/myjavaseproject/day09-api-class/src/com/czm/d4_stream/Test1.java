package com.czm.d4_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test1 {
    /*
     1、Stream（⚠️重点）
      也叫 Stream 流，是 jdk8 开始新增的一套API (java.util.stream.*)，可以用于操作 集合 或者 数组 的数据。
      Stream 流代表一条流水线，并能与数据源建立连接。调用流水线 的各种方法对数据进行 处理、计算。返回一个新的 Stream。最后可获取流的处理结果。

      特点：
        延迟执行，Stream的中间方法只有在终结操作的时候才会执行；
        Stream不会改变数据源，每次都会返回一个新的流；
        不存储数据，Stream不是一种数据结构，它是通过管道拿取数据源进行操作，产生新的流；
        可并行，可以通过 parallelStream() 去并行处理，充分利用了多核的优势；--- 适合对顺序没有要求场景！

      优势：
         Stream 流大量的结合了 Lambda 的语法风格来编程，功能强大，性能高效，代码简洁，可读性好。

      与for对比：
        代码简洁，但是debug调试比for麻烦；
        性能，处理简单操作或数据量比较小的情况，性能比for慢；适合处理复杂的操作和大量数据的场景下。
        Stream可进行并行操作，for需要自己实现线程池。

     2、Stream 流的使用步骤
      数据源(集合/数组/...) => 过滤 => 排序 => 去重 => (...) => 获取结果
        步骤1，获取数组、集合的 Stream 流
          stream()，获取集合对象的流；
          Arrays.stream(数组对象)，获取数组对象的流
          Stream.of(可变参数)，获取数组对象的流
          parallelStream()，获取并行流；

        步骤2，中间操作，返回新的 Stream 流，可以进行链式编程。如下所示：
          filter，对集合数据进行过滤
          map，转换，把流上的数据转换成新类型的数据
          sorted，排序
          limit，获取前几条
          skip，跳过前几条
          distinct，去重复
          Stream.concat(s1, s2)，合并流 s1 和 s2。

        步骤3，终结操作
          a、终结方法：
            forEach，遍历
            count，统计个数
            max，获取最大元素
            min，获取最小元素

          b、收集 Stream 流：
            collect(Collectors.toList()/.toSet()/.toMap、Collectors.groupingBy(key))，放入集合
            toList()，JDK16+，收集放入不可变集合
            toArray()，收集到数据

     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("张三丰");
        list.add("曹操");
        list.add("张无忌");
        list.add("张帅");

        // 案例：查找处理 姓张，名字是3个字的元素，并组成数组返回

        // 方式1：通过增强for循环遍历
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (s.startsWith("张") && s.length() == 3) {
                newList.add(s);
            }
        }
        System.out.println("--- newList = " + newList);

        // 方式2：使用 Stream 流改变
        List<String> newList2 = list.stream()
                                    .filter(s -> s.startsWith("张") && s.length() == 3)
                                    .collect(Collectors.toList());
        System.out.println("--- newList2 = " + newList2);

    }
}
