package com.czm.d1_properties;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;
import java.util.Set;

public class Test1 {
    /*
     1、特殊文件
        普通文件，如：.txt文件，只适合信息的记录和阅读，不适合信息数据的处理。
        特殊文件：如：.properties 属性文件、.xml XML文件等。存储有关系的数据，作为系统的配置文件、或者作为信息传输。

     2、Properties 属性
        Properties 是一个Map集合(键值对集合)，但是我们一般不会当集合使用。
        作用：Properties 是用来代表属性文件的，通过 Properties 可以读写属性文件里的内容。

     3、.properties 属性文件的特点：
            a、都只能是键值对；
            b、键不能重复；
            c、文件后缀一般是 .properties 结尾；
     */
    public static void main(String[] args) throws Exception {

        // 1、创建属性集对象
        Properties prop = new Properties();

        // 2、通过 字节流/字符流 加载属性文件到属性集对象中
//        prop.load(new FileInputStream("day11-special-file-log-code/src/users.properties"));
        prop.load(new FileReader("day11-special-file-log-code/src/users.properties"));
        /*
         注意：
            如果使用 FileInputStream 字节输入流加载数据，依然会出现中文乱码。
            转换用 FileReader 字符输入流后，将不会有中文乱码
         */


        // 3、打印属性对象的信息
        System.out.println(prop);

        // 4、读取属性文件中的信息
        System.out.println();
        System.out.println("--- 1、遍历 properties 对象");
        prop.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });

        System.out.println();
        System.out.println("--- 2、根据key遍历值");
        // 获取全部键集合
        Set<String> keys = prop.stringPropertyNames();
        for (String key : keys) {
            // 根据键获取值
            String value = prop.getProperty(key);
            System.out.println(key + "=" + value);
        }

    }
}
