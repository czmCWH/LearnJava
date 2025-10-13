package com.czm.d1_properties;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

public class Test3 {
    /*
     1、
     2、
     */
    public static void main(String[] args) throws Exception {
        // 1、创建 Properties 属性集对象
        Properties prop = new Properties();

        // 2、加载属性文件的键值对数据
//        prop.load(new FileInputStream(""));
        prop.load(new FileReader("day11-special-file-log-code/src/people.properties"));

        // 3、修改指定key的数据
        if (prop.containsKey("李芳")) {
            System.out.println(prop.getProperty("李芳"));
            prop.setProperty("李芳", "18");
        }

        // 4、把属性对象的数据保存到属性文件中
        prop.store(new FileOutputStream("day11-special-file-log-code/src/people.properties"), "修改了李芳的信息");
//        prop.store(new FileWriter("day11-special-file-log-code/src/people.properties"), "修改了李芳的信息");
    }
}
