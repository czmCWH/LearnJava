package com.czm.d3_collections_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 定义一个 牌 对象
// Lombok 插件：使用注解简化 get、set 有参、无参构造器的写法
// IDEA >= 2022
// Lombok 插件 需要.30版本以上，不能是.28版本
// ⚠️：需要开启 IDEA 对 Lombok 插件授权
// 授权：IDEA -> 构建、执行、部署 > 编译器 > 注解处理器，勾选 启用注解处理(Enable annotation processing)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    // 点数
    private String number;
    // 花色
    private String color;
    // 记录 牌的大小
    private int size;

    @Override
    public String toString() {
        return number + color;
    }
}
