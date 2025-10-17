package com.czm.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 部门实体类
 */
@Data
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
