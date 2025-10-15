package com.czm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义 User 实体类
 * ⚠️，开发中定义实体类的成员变量时，建议使用包装类型，因为Java中基本数据类型存在默认值，这样会与业务值无法正确区分，而包装类型的默认值为 null。
 */
@Data   // 生成 set/get 方法
@AllArgsConstructor  // 生成全参构造器
@NoArgsConstructor  // 生成无参构造器
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
}
