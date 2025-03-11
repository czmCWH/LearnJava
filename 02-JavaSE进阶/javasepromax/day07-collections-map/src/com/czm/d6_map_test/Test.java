package com.czm.d6_map_test;

import java.util.*;

public class Test {
    /*
     1、Map集合的案例-统计投票人数
        班级80名学生，组织秋游活动，四个景点依次是(A、B、C、D)，每个学生只能选择一个景点。
        请统计出最终哪个景点想去的人数最多。

     2、
     3、
     */
    public static void main(String[] args) {
        // 1、定义4个景点
        String[] locations = {"呼伦贝尔大草原", "三亚", "玉龙雪山", "赛里木湖"};

        // 2、随机造出 80 个学生想去景点的数据
        List<String> data = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 80; i++) {
            // 创建随机景点，并添加
            int index = r.nextInt(locations.length);
            data.add(locations[index]);
        }
        System.out.println("--- 80个学生想去的景点 = " + data);

        // 3、统计那个景点得票最高
        Map<String, Integer> mp = new HashMap<>();
        for (String location : data) {
            // 4、判断是否添加过景点
            if (mp.containsKey(location)) {
                mp.put(location, mp.get(location) + 1);
            } else {
                // 景点第一次被选择
                mp.put(location, 1);
            }
        }
        mp.forEach((k, v) -> {
            System.out.println(k + " 选择的人数 = " + v);
        });

    }
}
