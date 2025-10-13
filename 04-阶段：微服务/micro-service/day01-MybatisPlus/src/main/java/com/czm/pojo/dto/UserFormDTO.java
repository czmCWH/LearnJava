package com.czm.pojo.dto;

import lombok.Data;

/**
 * 用户表单实体
 */

@Data
public class UserFormDTO {

    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 注册手机号
    private String phone;

    // 详细信息，JSON风格
    private String info;

    // 账户余额
    private Integer balance;
}
