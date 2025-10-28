package com.czm.pojo.query;

import lombok.Data;

/**
 * 用户查询条件实体
 * 查询字段可以全部为 null
 */
@Data
public class UserQuery {
    // 用户名关键字
    private String name;

    // 用户状态：1-正常，2-冻结
    private Integer status;

    // 余额最小值
    private Integer minBalance;

    // 余额最大值
    private Integer maxBalance;
}
