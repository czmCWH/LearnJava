package com.czm.entity;

//import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 实现一个实体类，用于封装数据。
 */
//  lombok 依赖的相关注解：
//@Data     // 实现所有属性的 get/set 方法
//@NoArgsConstructor  // 用于生成无参构造器
//@AllArgsConstructor // 用于生成所有有参构造器
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime updateTime;

    public Dept() {
    }

    public Dept(Integer id, String name, LocalDateTime updateTime) {
        this.id = id;
        this.name = name;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
