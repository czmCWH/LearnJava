package com.czm.entity;

//import lombok.Data;

import java.time.LocalDateTime;

/**
 * 实现一个实体类，用于封装数据。
 */

//@Data     // 可以直接通过 lombok 依赖实现 get/set 方法
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
