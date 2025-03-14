package com.czm.d1_properties;

import java.io.FileOutputStream;
import java.util.Properties;

public class Test2 {

    // 使用 Properties 属性集对象把 键值对数据写入到 属性文件中

    public static void main(String[] args) throws Exception {

        // 1、创建属性集对象
        Properties prop = new Properties();

        // 2、添加键值对数据
        prop.setProperty("name", "哈哈大佬");   // prop.put("", "");
        prop.setProperty("height", "1.75");
        prop.setProperty("info", "abcdef123");

        // 3、把数据存储到文件
        prop.store(new FileOutputStream("day11-special-file-log-code/src/info.properties"), "保存的注释信息");

    }
}
