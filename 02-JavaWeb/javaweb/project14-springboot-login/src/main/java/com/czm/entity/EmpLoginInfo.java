package com.czm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装登录结果的实体类
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

