package com.czm.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.czm.enums.UserStatus;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * User 表实体类
 */

@Data
@TableName(value = "user", autoResultMap = true)    // 设置结果自动映射
public class User {

    /*
      IdType 枚举用于指定主键值生成策略；
        AUTO，随数据自动增长
        NONE(1),
        INPUT(2), 需要通过 set 方法自行指定；(避免同时多次插入)
        ASSIGN_ID(3)，自动分配数值字符串ID。即使用接口 IdentifierGenerator 的方法 nextId 来生成 id，默认实现为 DefaultIdentifierGenerator 雪花算法。
        ASSIGN_UUID(4);
     */

    // 用户id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 注册手机号
    private String phone;

    // 详细信息
    private String info;
//    @TableField(typeHandler = JacksonTypeHandler.class)   // 定义类型处理器
//    private UserInfo info;

    // 使用状态（1正常 2冻结）
//    private Integer status;
    private UserStatus status;


    // 账户余额
    private Integer balance;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;
}
