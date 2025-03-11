package com.czm.d7_map;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Test4 {

    // 案例：集合的嵌套
    // 定义一个 Map 集合存储省份城市信息

    public static void main(String[] args) {

        // 1、定义 Map 集合存储省份所有城市信息
        Map<String, List<String>> provinces = new HashMap<>();

        // 2、存入省份信息
        List<String> cities1 = new ArrayList<>();
        Collections.addAll(cities1, "南京市", "无锡市", "苏州市");
        provinces.put("江苏省", cities1);

        List<String> cities2 = new ArrayList<>();
        Collections.addAll(cities2, "武汉市", "襄阳市", "十堰市");
        provinces.put("湖北省", cities2);

        List<String> list = provinces.get("湖北省");
        // 增强for循环
        for (String s : list) {
            System.out.println("---" + s);
        }

    }
}
