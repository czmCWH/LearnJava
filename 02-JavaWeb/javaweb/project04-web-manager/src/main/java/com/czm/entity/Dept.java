package com.czm.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 实现一个实体类，用于封装数据。
 * ⚠️，开发中定义实体类的成员变量时，建议使用包装类型，因为Java中基本数据类型存在默认值，这样会与业务值无法正确区分，而包装类型的默认值为 null。
 */

//  lombok 依赖用于简化实体类的定义，相关注解如下：
@Data     // 实现所有属性的 get/set 方法
@NoArgsConstructor  // 用于生成无参构造器
@AllArgsConstructor // 用于生成所有有参构造器
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime updateTime;

}
