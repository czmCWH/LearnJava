package com.czm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录请求成功实体类
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpLoginInfo {
    private Integer id;
    private String username;
    private String name;
    private String token;

}

